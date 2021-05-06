package de.java8tutorial.javaEnum;

public enum DownloadStatus {
    CONNECTING,
    READING,
    DONE,
    ERROR;

    public static void main(String[] args) {
        System.out.println(DownloadStatus.DONE);
    }
}
