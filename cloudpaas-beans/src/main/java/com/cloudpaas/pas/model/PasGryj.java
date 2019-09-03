package com.cloudpaas.pas.model;

import javax.persistence.*;

@Table(name = "t_gryj")
public class PasGryj {
	
    @Id
    private Integer id;

    private String jgdh;

    private String jgmc;

    private String hydh;

    private String hymc;

    private String zbmc;

    private String bz;

    private String sdbs;

    private Double zbz;

    private String zbdw;

    private Double khjs;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return jgdh
     */
    public String getJgdh() {
        return jgdh;
    }

    /**
     * @param jgdh
     */
    public void setJgdh(String jgdh) {
        this.jgdh = jgdh;
    }

    /**
     * @return jgmc
     */
    public String getJgmc() {
        return jgmc;
    }

    /**
     * @param jgmc
     */
    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    /**
     * @return hydh
     */
    public String getHydh() {
        return hydh;
    }

    /**
     * @param hydh
     */
    public void setHydh(String hydh) {
        this.hydh = hydh;
    }

    /**
     * @return hymc
     */
    public String getHymc() {
        return hymc;
    }

    /**
     * @param hymc
     */
    public void setHymc(String hymc) {
        this.hymc = hymc;
    }

    /**
     * @return zbmc
     */
    public String getZbmc() {
        return zbmc;
    }

    /**
     * @param zbmc
     */
    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    /**
     * @return bz
     */
    public String getBz() {
        return bz;
    }

    /**
     * @param bz
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * @return sdbs
     */
    public String getSdbs() {
        return sdbs;
    }

    /**
     * @param sdbs
     */
    public void setSdbs(String sdbs) {
        this.sdbs = sdbs;
    }

    /**
     * @return zbz
     */
    public Double getZbz() {
        return zbz;
    }

    /**
     * @param zbz
     */
    public void setZbz(Double zbz) {
        this.zbz = zbz;
    }

    /**
     * @return zbdw
     */
    public String getZbdw() {
        return zbdw;
    }

    /**
     * @param zbdw
     */
    public void setZbdw(String zbdw) {
        this.zbdw = zbdw;
    }

    /**
     * @return khjs
     */
    public Double getKhjs() {
        return khjs;
    }

    /**
     * @param khjs
     */
    public void setKhjs(Double khjs) {
        this.khjs = khjs;
    }
}