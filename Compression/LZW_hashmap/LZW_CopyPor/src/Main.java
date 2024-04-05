public class Main {
    public static void main(String args[]) {
        String InputUse = "inputUse.use";
        String EncodeUse = "encodeUse.lzw";
        String OutputUse = "decodeUSe.use";

        String InputPdf = "inputPdf.pdf";
        String EncodePdf = "encodeUse.lzw";
        String Outputpdf = "decodeList.pdf";


        LZW lzwUse = new LZW(InputUse,EncodeUse,OutputUse);
        lzwUse.compressFile();
        lzwUse.decompressFile();

        LZW lzwPdf = new LZW(InputPdf,EncodePdf,Outputpdf);
        lzwPdf.compressFile();
        lzwPdf.decompressFile();

    }
}