import  java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        FileInputStream stream = new FileInputStream("res/input.wav");
        int length = stream.available();
        byte[] data = new byte[length];
        stream.read(data);
        stream.close();

        RiffHeader riffHeader = new RiffHeader();
        SubChunk1 subChunk1 = new SubChunk1();
        SubChunk2 subChunk2 = new SubChunk2();

        riffHeader.chunkId = new byte[4];
        riffHeader.chunkSize = new byte[4];
        riffHeader.format = new byte[4];

        subChunk1.subChunk1Id = new byte[4];
        System.out.println(data[4]);
        String result = "";
        OutputStreamWriter stream1 = new OutputStreamWriter( new FileOutputStream("res/Result.wav") );

        stream1.write(result);
        stream1.close();
    }
}
