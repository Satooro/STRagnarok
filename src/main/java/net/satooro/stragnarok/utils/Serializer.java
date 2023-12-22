package net.satooro.stragnarok.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Serializer {

    public static String itemstackArrayToBase64(PlayerInventory p){
        StringBuilder inventoryString = new StringBuilder();
        Arrays.stream(p.getContents()).toList().forEach(item -> {
            if (item != null) inventoryString.append(itemStackToBase64(item)).append(".");
        });
        return inventoryString.toString();
    }

    public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
        String[] stringItems = data.split("\\.");
        ItemStack[] items = new ItemStack[stringItems.length];

        for(int i = 0; i < stringItems.length; i++){
            items[i] = itemStackFromBase64(stringItems[i]);
        }

        return items;
    }

    public static String itemStackToBase64(ItemStack item) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(item);

            dataOutput.close();
            return new String(Base64Coder.encode(outputStream.toByteArray()));
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public static ItemStack itemStackFromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decode(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack item;

            item = (ItemStack) dataInput.readObject();

            dataInput.close();
            return item;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

}
