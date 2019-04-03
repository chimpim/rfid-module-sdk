package com.chimpim.rfidmodule.internal;

import com.sun.jna.Library;
import com.sun.jna.Native;


public interface JnaModuleAPI extends Library {

    JnaModuleAPI INSTANCE = Native.load(Utils.getJnaLibraryName(), JnaModuleAPI.class);

    int InitReader(int[] hReader, String src, int rtype);

    int InitReader_Notype(int[] hReader, String src, int rtype);

    void CloseReader(int hReader);

    int GetTagData(int hReader, int ant, char bank, int address, int blkcnt, byte[] data, byte[] accesspasswd, short timeout);

    int WriteTagData(int hReader, int ant, char bank, int address, byte[] data, int datalen, byte[] accesspasswd, short timeout);

    int WriteTagEpcEx(int hReader, int ant, byte[] Epc, int epclen, byte[] accesspwd, short timeout);

    int TagInventory_Raw(int hReader, int[] ants, int antcnt, short timeout, int[] tagcnt);

    int TagInventory_BaseType(int hReader, int[] ants, int antcnt, short timeout, char[] outbuf, int[] tagcnt);

    int GetNextTag_BaseType(int hReader, byte[] outbuf);

    int LockTag(int hReader, int ant, byte lockobjects, short locktypes, byte[] accesspasswd, short timeout);

    int KillTag(int hReader, int ant, byte[] killpasswd, short timeout);

    int Lock180006BTag(int hReader, int ant, int startblk, int blkcnt, short timeout);

    int BlockPermaLock(int hReader, int ant, int readlock, int startblk, int blkrange, byte[] mask, byte[] pwd, short timeout);

    int BlockErase(int hReader, int ant, int bank, int wordaddr, int wordcnt, byte[] pwd, short timeout);

    int EraseDataOnReader(int hReader);

    int SaveDataOnReader(int hReader, int address, byte[] data, int datalen);

    int ReadDataOnReader(int hReader, int address, byte[] data, int datalen);

    int CustomCmd_BaseType(int hReader, int ant, int cmdtype, byte[] CustomPara, byte[] CustomRet);

    int ParamGet(int hReader, int key, Object val);

    int ParamSet(int hReader, int key, Object val);

    int SetGPO(int hReader, int gpoid, int val);

    int GetGPI(int hReader, int gpoid, int[] val);

    int PsamTransceiver(int hReader, int soltid, int coslen, byte[] cos, int[] cosresplen, byte[] cosresp, byte[] errcode, short timeout);


}
