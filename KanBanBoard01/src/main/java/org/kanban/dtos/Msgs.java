package org.kanban.dtos;

public class Msgs {

    private String mensaje;

    public Msgs(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}