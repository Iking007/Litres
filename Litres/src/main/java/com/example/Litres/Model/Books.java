package com.example.Litres.Model;

public class Books {

    private Long id;

    private String title, img, download, str, writer;
    private int save;

    public Books() {
    }

    public Books(String title, String img, String download, String str, String user) {
        this.title = title;
        this.img = img;
        this.download = download;
        this.str = str;
        this.writer = user;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public int getSave() {
        return save;
    }
    public void setSave(int save) {
        this.save = save;
    }

    public String getDownload() {
        return download;
    }
    public void setDownload(String download) {
        this.download = download;
    }

    public String getStr() {
        return str;
    }
    public void setStr(String str) {
        this.str = str;
    }

    public String getWriter() {
        return this.writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
}
