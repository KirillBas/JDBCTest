package ru.basharin.util;

import ru.basharin.model.Rectangle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

final class InfoStreamWriter {

    private InfoStreamWriter() {
        throw new RuntimeException("never create");
    }

    // TODO: 25.11.2017 переписать для Treangle
    static void serialize(List<Rectangle> rectangles, OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(rectangles.size());
        for (Rectangle sides : rectangles) {
            dataOutputStream.writeInt(sides.getSide1().getBytes().length);
            dataOutputStream.write(sides.getSide1().getBytes());
            dataOutputStream.writeInt(sides.getSide2().getBytes().length);
            dataOutputStream.write(sides.getSide2().getBytes());
            dataOutputStream.writeInt(sides.getSide3());
            dataOutputStream.writeInt(sides.getSide4());
        }
        dataOutputStream.flush();
    }

    static List<Rectangle> deSerialize(InputStream inputStream) throws IOException {
//        StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(inputStream)));
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int size = dataInputStream.readInt();
        List<Rectangle> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int side1Length = dataInputStream.readInt();
            byte[] side1Bytes = new byte[side1Length];
            dataInputStream.read(side1Bytes, 0, side1Length);
            String side1 = new String(side1Bytes);

            int side2Length = dataInputStream.readInt();
            byte[] side2Bytes = new byte[side2Length];
            dataInputStream.read(side2Bytes, 0, side2Length);
            String side2 = new String(side2Bytes);

            int side3 = dataInputStream.readInt();
            int side4 = dataInputStream.readInt();
            result.add(new Rectangle(side1, side2, side3, side4));
        }
        return result;
    }
}
