package com.uhf.api.cls;

import com.chimpim.rfidmodule.internal.Utils;

public class JniModuleAPI {

    static {
        System.loadLibrary(Utils.getJniLibraryName());
    }

    public static final JniModuleAPI INSTANCE = new JniModuleAPI();

    public native int InitReader(int[] hReader, String src, int rtype);

    public native int InitReader_Notype(int[] hReader, String src, int rtype);

    public native void CloseReader(int hReader);

    public native int GetTagData(int hReader, int ant, char bank, int address, int blkcnt, byte[] data, byte[] accesspasswd, short timeout);

    public native int WriteTagData(int hReader, int ant, char bank, int address, byte[] data, int datalen, byte[] accesspasswd, short timeout);

    public native int WriteTagEpcEx(int hReader, int ant, byte[] Epc, int epclen, byte[] accesspwd, short timeout);

    public native int TagInventory_Raw(int hReader, int[] ants, int antcnt, short timeout, int[] tagcnt);

    public native int TagInventory_BaseType(int hReader, int[] ants, int antcnt, short timeout, char[] outbuf, int[] tagcnt);

    public native int GetNextTag_BaseType(int hReader, byte[] outbuf);

    public native int LockTag(int hReader, int ant, byte lockobjects, short locktypes, byte[] accesspasswd, short timeout);

    public native int KillTag(int hReader, int ant, byte[] killpasswd, short timeout);

    public native int Lock180006BTag(int hReader, int ant, int startblk, int blkcnt, short timeout);

    public native int BlockPermaLock(int hReader, int ant, int readlock, int startblk, int blkrange, byte[] mask, byte[] pwd, short timeout);

    public native int BlockErase(int hReader, int ant, int bank, int wordaddr, int wordcnt, byte[] pwd, short timeout);

    public native int EraseDataOnReader(int hReader);

    public native int SaveDataOnReader(int hReader, int address, byte[] data, int datalen);

    public native int ReadDataOnReader(int hReader, int address, byte[] data, int datalen);

    public native int CustomCmd_BaseType(int hReader, int ant, int cmdtype, byte[] CustomPara, byte[] CustomRet);

    public native int ParamGet(int hReader, int key, Object val);

    public native int ParamSet(int hReader, int key, Object val);

    public native int SetGPO(int hReader, int gpoid, int val);

    public native int GetGPI(int hReader, int gpoid, int[] val);

    public native int PsamTransceiver(int hReader, int soltid, int coslen, byte[] cos, int[] cosresplen, byte[] cosresp, byte[] errcode, short timeout);
}
