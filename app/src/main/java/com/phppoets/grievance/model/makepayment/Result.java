package com.phppoets.grievance.model.makepayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Result
{

    @SerializedName("MERCHANTCODE")
    @Expose
    private String mERCHANTCODE;
    @SerializedName("PRN")
    @Expose
    private String pRN;
    @SerializedName("REQTIMESTAMP")
    @Expose
    private String rEQTIMESTAMP;
    @SerializedName("PURPOSE")
    @Expose
    private String pURPOSE;
    @SerializedName("AMOUNT")
    @Expose
    private String aMOUNT;
    @SerializedName("SUCCESSURL")
    @Expose
    private String sUCCESSURL;
    @SerializedName("FAILUREURL")
    @Expose
    private String fAILUREURL;
    @SerializedName("CANCELURL")
    @Expose
    private String cANCELURL;
    @SerializedName("USERNAME")
    @Expose
    private String uSERNAME;
    @SerializedName("USERMOBILE")
    @Expose
    private String uSERMOBILE;
    @SerializedName("USEREMAIL")
    @Expose
    private Object uSEREMAIL;
    @SerializedName("CHECKSUM")
    @Expose
    private String cHECKSUM;

    public String getMERCHANTCODE()
    {
        return mERCHANTCODE;
    }

    public void setMERCHANTCODE(String mERCHANTCODE)
    {
        this.mERCHANTCODE = mERCHANTCODE;
    }

    public String getPRN()
    {
        return pRN;
    }

    public void setPRN(String pRN)
    {
        this.pRN = pRN;
    }

    public String getREQTIMESTAMP()
    {
        return rEQTIMESTAMP;
    }

    public void setREQTIMESTAMP(String rEQTIMESTAMP)
    {
        this.rEQTIMESTAMP = rEQTIMESTAMP;
    }

    public String getPURPOSE()
    {
        return pURPOSE;
    }

    public void setPURPOSE(String pURPOSE)
    {
        this.pURPOSE = pURPOSE;
    }

    public String getAMOUNT()
    {
        return aMOUNT;
    }

    public void setAMOUNT(String aMOUNT)
    {
        this.aMOUNT = aMOUNT;
    }

    public String getSUCCESSURL()
    {
        return sUCCESSURL;
    }

    public void setSUCCESSURL(String sUCCESSURL)
    {
        this.sUCCESSURL = sUCCESSURL;
    }

    public String getFAILUREURL()
    {
        return fAILUREURL;
    }

    public void setFAILUREURL(String fAILUREURL)
    {
        this.fAILUREURL = fAILUREURL;
    }

    public String getCANCELURL()
    {
        return cANCELURL;
    }

    public void setCANCELURL(String cANCELURL)
    {
        this.cANCELURL = cANCELURL;
    }

    public String getUSERNAME()
    {
        return uSERNAME;
    }

    public void setUSERNAME(String uSERNAME)
    {
        this.uSERNAME = uSERNAME;
    }

    public String getUSERMOBILE()
    {
        return uSERMOBILE;
    }

    public void setUSERMOBILE(String uSERMOBILE)
    {
        this.uSERMOBILE = uSERMOBILE;
    }

    public Object getUSEREMAIL()
    {
        return uSEREMAIL;
    }

    public void setUSEREMAIL(Object uSEREMAIL)
    {
        this.uSEREMAIL = uSEREMAIL;
    }

    public String getCHECKSUM()
    {
        return cHECKSUM;
    }

    public void setCHECKSUM(String cHECKSUM)
    {
        this.cHECKSUM = cHECKSUM;
    }
}