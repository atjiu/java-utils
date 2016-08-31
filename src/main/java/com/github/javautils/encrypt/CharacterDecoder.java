package com.github.javautils.encrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class CharacterDecoder {

    public CharacterDecoder() {
    }

    protected abstract int bytesPerAtom();

    protected abstract int bytesPerLine();

    protected void decodeBufferPrefix(InputStream inputstream, OutputStream outputstream)
            throws IOException {
    }

    protected void decodeBufferSuffix(InputStream inputstream, OutputStream outputstream)
            throws IOException {
    }

    protected int decodeLinePrefix(InputStream inputstream, OutputStream outputstream)
            throws IOException {
        return bytesPerLine();
    }

    protected void decodeLineSuffix(InputStream inputstream, OutputStream outputstream)
            throws IOException {
    }

    protected void decodeAtom(InputStream inputstream, OutputStream outputstream, int i)
            throws IOException {
        throw new IOException("StreamExhausted");
    }

    protected int readFully(InputStream inputstream, byte abyte0[], int i, int j)
            throws IOException {
        for (int k = 0; k < j; k++) {
            int l = inputstream.read();
            if (l == -1)
                return k != 0 ? k : -1;
            abyte0[k + i] = (byte) l;
        }

        return j;
    }

    @SuppressWarnings("unused")
    public void decodeBuffer(InputStream inputstream, OutputStream outputstream)
            throws IOException {
        int j = 0;
        decodeBufferPrefix(inputstream, outputstream);
        try {
            do {
                int k = decodeLinePrefix(inputstream, outputstream);
                int i;
                for (i = 0; i + bytesPerAtom() < k; i += bytesPerAtom()) {
                    decodeAtom(inputstream, outputstream, bytesPerAtom());
                    j += bytesPerAtom();
                }

                if (i + bytesPerAtom() == k) {
                    decodeAtom(inputstream, outputstream, bytesPerAtom());
                    j += bytesPerAtom();
                } else {
                    decodeAtom(inputstream, outputstream, k - i);
                    j += k - i;
                }
                decodeLineSuffix(inputstream, outputstream);
            } while (true);
        } catch (IOException e) {
            if (e.getMessage().equals("StreamExhausted"))
                decodeBufferSuffix(inputstream, outputstream);
            else
                throw e;
        }
    }

    public byte[] decodeBuffer(String s)
            throws IOException {
        byte abyte0[] = s.getBytes();
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        decodeBuffer(((InputStream) (bytearrayinputstream)), ((OutputStream) (bytearrayoutputstream)));
        return bytearrayoutputstream.toByteArray();
    }

    public String decodeStr(String s) throws IOException {
        return new String(decodeBuffer(s));
    }

    public byte[] decodeBuffer(InputStream inputstream)
            throws IOException {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        decodeBuffer(inputstream, ((OutputStream) (bytearrayoutputstream)));
        return bytearrayoutputstream.toByteArray();
    }
}