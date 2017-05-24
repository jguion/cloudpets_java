package com.google.android.gms.internal;

import com.spiraltoys.cloudpets2.model.VoiceMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@zziy
class zzjg {
    private String zzae;
    private final String zzcaj;
    private int zzcdb;
    private final List<String> zzcli;
    private final List<String> zzclj;
    private final String zzclk;
    private final String zzcll;
    private final String zzclm;
    private final String zzcln;
    private final boolean zzclo;
    private final boolean zzclp;
    private final String zzclq;

    public zzjg(int i, Map<String, String> map) {
        this.zzae = (String) map.get("url");
        this.zzcll = (String) map.get("base_uri");
        this.zzclm = (String) map.get("post_parameters");
        this.zzclo = parseBoolean((String) map.get("drt_include"));
        this.zzclp = parseBoolean((String) map.get("pan_include"));
        this.zzclk = (String) map.get("activation_overlay_url");
        this.zzclj = zzck((String) map.get("check_packages"));
        this.zzcaj = (String) map.get("request_id");
        this.zzcln = (String) map.get(VoiceMessage.TYPE);
        this.zzcli = zzck((String) map.get("errors"));
        this.zzcdb = i;
        this.zzclq = (String) map.get("fetched_ad");
    }

    private static boolean parseBoolean(String str) {
        return str != null && (str.equals("1") || str.equals("true"));
    }

    private List<String> zzck(String str) {
        return str == null ? null : Arrays.asList(str.split(","));
    }

    public int getErrorCode() {
        return this.zzcdb;
    }

    public String getRequestId() {
        return this.zzcaj;
    }

    public String getType() {
        return this.zzcln;
    }

    public String getUrl() {
        return this.zzae;
    }

    public void setUrl(String str) {
        this.zzae = str;
    }

    public List<String> zzsg() {
        return this.zzcli;
    }

    public String zzsh() {
        return this.zzclm;
    }

    public boolean zzsi() {
        return this.zzclo;
    }

    public String zzsj() {
        return this.zzclq;
    }
}
