/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetin_oldhouse;

import java.time.LocalDateTime;

/**
 *
 * @author igorg
 */
public class Check {
    private String tag;
    private LocalDateTime time;
    private Integer id;

    public Check(String tag, LocalDateTime time) {
        this.tag = tag;
        this.time = time;
        this.id = null;
    }

    public Check(Integer id, String tag, LocalDateTime time) {
        this.tag = tag;
        this.time = time;
        this.id = id;
    }
    

    public String getTag() {
        return tag;
    }

    public LocalDateTime getTime() {
        return time;
    }


   /* @Override
    public String toString() {
       
        if (in != null) {
            if (in) {
                return "MATRÍCULA: [" + matricula + "] UID: [" + uid + "] ENTROU às " + time.format(Util.TIME_FORMATTER);
            } else {
                return "MATRÍCULA: [" + matricula + "] UID: [" + uid + "] SAIU às " + time.format(Util.TIME_FORMATTER);
            }
        } else {
            return "UID: [" + uid + "] NEGADO às " + time.format(Util.TIME_FORMATTER);
        }
    }*/
}
