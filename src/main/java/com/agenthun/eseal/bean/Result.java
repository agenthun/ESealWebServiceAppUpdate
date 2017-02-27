
package com.agenthun.eseal.bean;

import java.io.Serializable;

public class Result implements Serializable {

    private Integer RESULT;
    private Integer EFFECTIVETOKEN;
    private String ERRORINFO;
    private Integer ICOUNT;

    public Result(Integer RESULT, Integer EFFECTIVETOKEN, String ERRORINFO, Integer ICOUNT) {
        this.RESULT = RESULT;
        this.EFFECTIVETOKEN = EFFECTIVETOKEN;
        this.ERRORINFO = ERRORINFO;
        this.ICOUNT = ICOUNT;
    }

    public Integer getRESULT() {
        return RESULT;
    }

    public void setRESULT(Integer RESULT) {
        this.RESULT = RESULT;
    }

    public Integer getEFFECTIVETOKEN() {
        return EFFECTIVETOKEN;
    }

    public void setEFFECTIVETOKEN(Integer EFFECTIVETOKEN) {
        this.EFFECTIVETOKEN = EFFECTIVETOKEN;
    }

    public String getERRORINFO() {
        return ERRORINFO;
    }

    public void setERRORINFO(String ERRORINFO) {
        this.ERRORINFO = ERRORINFO;
    }

    public Integer getICOUNT() {
        return ICOUNT;
    }

    public void setICOUNT(Integer ICOUNT) {
        this.ICOUNT = ICOUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (!RESULT.equals(result.RESULT)) return false;
        return EFFECTIVETOKEN.equals(result.EFFECTIVETOKEN);

    }

    @Override
    public int hashCode() {
        int result = RESULT.hashCode();
        result = 31 * result + EFFECTIVETOKEN.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "RESULT=" + RESULT +
                ", EFFECTIVETOKEN=" + EFFECTIVETOKEN +
                ", ERRORINFO='" + ERRORINFO + '\'' +
                ", ICOUNT=" + ICOUNT +
                '}';
    }
}
