import  java.io.*;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String config = in.nextLine();
        String inputFileName = config.substring(0, config.indexOf(" "));
        String outputFileName = config.substring(config.indexOf(" ") + 1, config.lastIndexOf(" "));
        int number = Integer.parseInt(config.substring(config.lastIndexOf(" ") + 1));

        DataInput input = new DataInputStream(new FileInputStream("res/" + inputFileName));
        DataOutput output = new DataOutputStream(new FileOutputStream("res/" + outputFileName));

        FileInputStream stream = new FileInputStream("res/input.wav");
        int length = stream.available();
        stream.close();


        RiffHeader riffHeader = new RiffHeader();
        SubChunk1 subChunk1 = new SubChunk1();
        SubChunk2 subChunk2 = new SubChunk2();

        riffHeader.chunkId = new int[4];
        riffHeader.chunkSize = new int[4];
        riffHeader.format = new int[4];

        subChunk1.subChunk1Id = new int[4];
        subChunk1.subChunkSize = new int[4];
        subChunk1.audioFormat = new int[2];
        subChunk1.numChannels = new int[2];
        subChunk1.sampleRate = new int[4];
        subChunk1.byteRate = new int[4];
        subChunk1.blockAlign = new int[2];
        subChunk1.bitsPerSample = new int[2];

        subChunk2.subChunk2Id = new int[4];
        subChunk2.subChunk2Size = new int[4];
        subChunk2.data = new int[length - 44];

        int copy[] = new int[4];
        int newData[] = new int[subChunk2.data.length * number];
        int newWav[] = new int[newData.length + 44];
        int k = 0;
        int w = 0;


        for (int i = 0; i < 4; i++, w++) {
            riffHeader.chunkId[i] = input.readByte();
            newWav[w] = riffHeader.chunkId[i];
        }

        for (int i = 0; i < 4; i++, w++) {
            riffHeader.chunkSize[i] = input.readByte();
            newWav[w] = riffHeader.chunkSize[i];
        }

        for (int i = 0; i < 4; i++, w++) {
            riffHeader.format[i] = input.readByte();
            newWav[w] = riffHeader.format[i];
        }

        for (int i = 0; i < 4; i++, w++) {
            subChunk1.subChunk1Id[i] = input.readByte();
            newWav[w] = subChunk1.subChunk1Id[i];
        }

        for (int i = 0; i < 4; i++, w++) {
            subChunk1.subChunkSize[i] = input.readByte();
            newWav[w] = subChunk1.subChunkSize[i];
        }

        for (int i = 0; i < 2; i++, w++) {
            subChunk1.audioFormat[i] = input.readByte();
            newWav[w] = subChunk1.audioFormat[i];
        }

        for (int i = 0; i < 2; i++, w++) {
            subChunk1.numChannels[i] = input.readByte();
            newWav[w] = subChunk1.numChannels[i];
        }

        for (int i = 0; i < 4; i++, w++) {
            subChunk1.sampleRate[i] = input.readByte();
            newWav[w] = subChunk1.sampleRate[i];
        }

        for (int i = 0; i < 4; i++, w++) {
            subChunk1.byteRate[i] = input.readByte();
            newWav[w] = subChunk1.byteRate[i];
        }

        for (int i = 0; i < 2; i++, w++) {
            subChunk1.blockAlign[i] = input.readByte();
            newWav[w] = subChunk1.blockAlign[i];
        }

        for (int i = 0; i < 2; i++, w++) {
            subChunk1.bitsPerSample[i] = input.readByte();
            newWav[w] = subChunk1.bitsPerSample[i];
        }

        for (int i = 0; i < 4; i++, w++) {
            subChunk2.subChunk2Id[i] = input.readByte();
            newWav[w] = subChunk2.subChunk2Id[i];
        }

        for (int i = 0; i < 4; i++, w++) {
            subChunk2.subChunk2Size[i] = input.readByte();
            newWav[w] = subChunk2.subChunk2Size[i];
        }

        for (int i = 0; i < subChunk2.data.length; i++) {
            subChunk2.data[i] = input.readByte();
        }


        for (int i = 0; i < subChunk2.data.length; ) {
            for (int j = 0; j < 4; j++, i++) {
                copy[j] = subChunk2.data[i];
            }
            for(int q = 0; q < number; q++) {
                for(int j = 0; j < 4; j++, k++) {
                    newData[k] = copy[j];
                }
            }
        }

        for(int i = 44, j = 0; i < newData.length; i++, j++) {
            newWav[i] = newData[j];
        }

        
        newWav[42] = 194;


        for(int i = 0; i < newWav.length; i++) {
            output.writeByte(newWav[i]);
        }



    }
}






