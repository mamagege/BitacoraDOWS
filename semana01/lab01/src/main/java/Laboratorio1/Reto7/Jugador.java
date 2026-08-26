package Reto7;

import java.util.Random;

public class Jugador {
    private static final String[] NOMBRES = {"Gihun", "Sangwoo", "Saebyeok", "Ali", "Ilnam", "Deoksu", "Minyeo",
            "Junho", "Hoseong", "Byeonggi", "Jiyeong", "Mintae", "Byeongchan",
            "Jihun", "Jungja", "Yeonghee", "Cheolsu", "Gyeongsu", "Daesu",
            "Minho", "Seojun", "Hajun", "Dohyun", "Eunwoo", "Siwoo", "Jiwoo",
            "Yejun", "Juwon", "Sion", "Yujin", "Minseo", "Seoyeon", "Jiwon",
            "Haeun", "Soyul", "Jiah", "Ahrin", "Dain", "Eunji", "Subin",
            "Hyejin", "Yoojin", "Nayeon", "Chaewon", "Minji", "Seohyun", "Yeji",
            "Sohee", "Eunjung", "Jiyoung", "Sunhee", "Minjung", "Haewon", "Soojin",
            "Youngsook", "Junghee", "Mikyung", "Kyunghee", "Jungsook", "Soonja",
            "Hwayoung", "Eunkyung", "Sunyoung", "Miyoung", "Younghee", "Kyungsook",
            "Donghyun", "Sungmin", "Joonho", "Woojin", "Jinwoo", "Hyunwoo",
            "Sanghyun", "Minsu", "Junseo", "Dojin", "Siwan", "Minhyuk", "Seungwoo",
            "Kyujin", "Taehyun", "Insung", "Dongwook", "Jihoon", "Sangwook",
            "Hyungsik", "Wonsik", "Kyungsoo", "Jungwoo", "Kyuwon", "Joohyuk",
            "Seungho", "Taewoo", "Jaehyun", "Woosung", "Jinhyuk", "Sangmin",
            "Gunwoo", "Sihyuk", "Minjae", "Yechan", "Sian", "Raon", "Yul",
            "Bada", "Hanu", "Hanul", "San", "Eul", "Daon", "Garam", "Nabi",
            "Chanwoo", "Seunghyun", "Jaewoo", "Minseok", "Junhyung", "Dongwook",
            "Sangjin", "Kyuho", "Taemin", "Inwoo", "Wooseok", "Jinho", "Hyunmin",
            "Junghyun", "Sunwoo", "Joowon", "Sijin", "Mingyu", "Seungmin", "Jaemin"};

    private int numero;
    private String nombre;
    private int deuda;
    private int premio = 0;
    private String estado = "ACTIVO";

    public Jugador(int numero){
        this.numero = numero;
        Random random = new Random();
        //Generacion numero aleatorio del 1 al 456
        this.numero = random.nextInt(456) + 1;

        //Generacion nombre al azar
        this.nombre = NOMBRES[random.nextInt(NOMBRES.length)];

        this.deuda = random.nextInt(340) + 1;

    }

    @Override
    public String toString() {
        return nombre + " (#" + numero + ")";
    }
    public int getNumero() {return numero;}
    public String getNombre(){return nombre;}
    public int getDeuda() {return deuda;}
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}








}

