package com.chimpim.rfidmodule;

import com.chimpim.rfidmodule.internal.JnaModuleAPI;

@SuppressWarnings("UnnecessaryLocalVariable")
class JnaReader implements Reader {

    private int[] hReader;

    JnaReader() {
        this.hReader = new int[1];
    }

    /**
     * @deprecated 不推荐使用
     */
    @Deprecated
    @Override
    public READER_ERR InitReader(String src, Reader_Type rtype) {
        int re = JnaModuleAPI.INSTANCE.InitReader(hReader, src, rtype.value());
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;

    }

    /**
     * 连接读写器
     *
     * @param src (网口或者串口)，读写器天线端口数
     */
    @Override
    public READER_ERR InitReader_Notype(String src, int rtype) {
        int re = JnaModuleAPI.INSTANCE.InitReader_Notype(hReader, src, rtype);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    /**
     * 关闭读写器
     */
    @Override
    public void CloseReader() {
        JnaModuleAPI.INSTANCE.CloseReader(hReader[0]);
    }

    @Override
    public READER_ERR GetTagData(int ant, char bank, int address,
                                        int blkcnt, byte[] data, byte[] accesspasswd, short timeout) {
        int re = JnaModuleAPI.INSTANCE.GetTagData(hReader[0], ant, bank, address, blkcnt, data, accesspasswd, timeout);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }


    @Override
    public READER_ERR WriteTagData(int ant, char bank, int address,
                                          byte[] data, int datalen, byte[] accesspasswd, short timeout) {
        int re = JnaModuleAPI.INSTANCE.WriteTagData(hReader[0], ant, bank, address, data, datalen, accesspasswd, timeout);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR WriteTagEpcEx(int ant, byte[] Epc,
                                           int epclen, byte[] accesspwd, short timeout) {
        int re = JnaModuleAPI.INSTANCE.WriteTagEpcEx(hReader[0], ant, Epc, epclen, accesspwd, timeout);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }


    @Override
    public READER_ERR TagInventory(int[] ants, int antcnt, short timeout,
                                          TAGINFO[] pTInfo, int[] tagcnt) {
        int re = JnaModuleAPI.INSTANCE.TagInventory_Raw(hReader[0], ants, antcnt, timeout, tagcnt);
        READER_ERR ERR = READER_ERR.valueOf(re);

        if (ERR == READER_ERR.MT_OK_ERR) {
            for (int i = 0; i < tagcnt[0]; i++) {
                TAGINFO pTInfoa = new TAGINFO();
                READER_ERR er = GetNextTag(pTInfoa);
                if (er == READER_ERR.MT_OK_ERR) {
                    pTInfo[i] = pTInfoa;
                }
            }
        }
        return ERR;
    }

    @Override
    public READER_ERR TagInventory_Raw(int[] ants, int antcnt,
                                              short timeout, int[] tagcnt) {
        int re = JnaModuleAPI.INSTANCE.TagInventory_Raw(hReader[0], ants, antcnt, timeout, tagcnt);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR TagInventory_BaseType(int[] ants, int antcnt,
                                                   short timeout, char[] outbuf, int[] tagcnt) {
        int re = JnaModuleAPI.INSTANCE.TagInventory_BaseType(hReader[0], ants, antcnt, timeout, outbuf, tagcnt);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR GetNextTag(TAGINFO TI) {
        byte[] tagbuf = new byte[230];
        int pos = 0;
        int re = JnaModuleAPI.INSTANCE.GetNextTag_BaseType(hReader[0], tagbuf);
        READER_ERR ERR = READER_ERR.valueOf(re);
        //TAGINFO TI;
        if (ERR == READER_ERR.MT_OK_ERR) {
            //TI = new TAGINFO();
            TI.ReadCnt = tagbuf[pos++];
            TI.RSSI = tagbuf[pos++];
            TI.AntennaID = tagbuf[pos++];
            TI.Frequency = (tagbuf[pos] << 24) | (tagbuf[pos + 1] << 16) |
                    (tagbuf[pos + 2] << 8) | tagbuf[pos + 3];
            pos += 4;
            TI.TimeStamp = (tagbuf[pos] << 24) | (tagbuf[pos + 1] << 16) |
                    (tagbuf[pos + 2] << 8) | tagbuf[pos + 3];
            pos += 4;
            TI.Res[0] = tagbuf[pos++];
            TI.Res[1] = tagbuf[pos++];
            int epclen = (tagbuf[pos] << 8) | tagbuf[pos + 1];
            pos += 2;
            TI.PC[0] = tagbuf[pos++];
            TI.PC[1] = tagbuf[pos++];
            TI.EpcId = new byte[epclen];
            System.arraycopy(tagbuf, pos, TI.EpcId, 0, epclen);
            pos += epclen;
            TI.CRC[0] = tagbuf[pos++];
            TI.CRC[1] = tagbuf[pos++];
            int emddatalen = (tagbuf[pos] << 8) | tagbuf[pos + 1];
            pos += 2;
            TI.EmbededData = new byte[emddatalen];
            if (emddatalen > 0)
                System.arraycopy(tagbuf, pos, TI.EmbededData, 0, emddatalen);


            //pTInfo=(TAGINFO) TI.clone();
        }
        return ERR;
    }

    @Override
    public READER_ERR GetNextTag_BaseType(byte[] outbuf) {
        int re = JnaModuleAPI.INSTANCE.GetNextTag_BaseType(hReader[0], outbuf);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR LockTag(int ant, byte lockobjects, short locktypes,
                                     byte[] accesspasswd, short timeout) {
        int re = JnaModuleAPI.INSTANCE.LockTag(hReader[0], ant, lockobjects, locktypes, accesspasswd, timeout);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR KillTag(int ant, byte[] killpasswd, short timeout) {
        int re = JnaModuleAPI.INSTANCE.KillTag(hReader[0], ant, killpasswd, timeout);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR Lock180006BTag(int ant, int startblk, int blkcnt,
                                            short timeout) {
        int re = JnaModuleAPI.INSTANCE.Lock180006BTag(hReader[0], ant, startblk, blkcnt, timeout);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR BlockPermaLock(int ant, int readlock, int startblk,
                                            int blkrange, byte[] mask, byte[] pwd, short timeout) {
        int re = JnaModuleAPI.INSTANCE.BlockPermaLock(hReader[0], ant, readlock, startblk, blkrange, mask, pwd, timeout);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR BlockErase(int ant, int bank, int wordaddr, int wordcnt,
                                        byte[] pwd, short timeout) {
        int re = JnaModuleAPI.INSTANCE.BlockErase(hReader[0], ant, bank, wordaddr, wordcnt, pwd, timeout);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR EraseDataOnReader() {
        int re = JnaModuleAPI.INSTANCE.EraseDataOnReader(hReader[0]);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR SaveDataOnReader(int address, byte[] data, int datalen) {
        int re = JnaModuleAPI.INSTANCE.SaveDataOnReader(hReader[0], address, data, datalen);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR ReadDataOnReader(int address, byte[] data, int datalen) {
        int re = JnaModuleAPI.INSTANCE.ReadDataOnReader(hReader[0], address, data, datalen);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR CustomCmd(int ant, CustomCmdType cmdtype, Object CustomPara, Object CustomRet) {
        byte[] para = null, ret = null;
        switch (cmdtype) {
            case IMPINJ_M4_Qt: {
                IMPINJM4QtPara CustomPara2 = (IMPINJM4QtPara) CustomPara;
                para = new byte[10];
                ret = new byte[10];
                System.arraycopy(CustomPara2.AccessPwd, 0, para, 0, 4);
                para[4] = (byte) CustomPara2.CmdType;
                para[5] = (byte) CustomPara2.MemType;
                para[6] = (byte) CustomPara2.PersistType;
                para[7] = (byte) CustomPara2.RangeType;
                para[8] = (byte) ((CustomPara2.TimeOut & 0xff00) >> 8);
                para[9] = (byte) (CustomPara2.TimeOut & 0x00ff);
            }
            break;
            case ALIEN_Higgs3_BlockReadLock: {
                para = new byte[7];
                ret = new byte[7];
                ALIENHiggs3BlockReadLockPara CustomPara2 = (ALIENHiggs3BlockReadLockPara) CustomPara;
                System.arraycopy(CustomPara2.AccessPwd, 0, para, 0, 4);
                para[4] = CustomPara2.BlkBits;
                para[5] = (byte) ((CustomPara2.TimeOut & 0xff00) >> 8);
                para[6] = (byte) (CustomPara2.TimeOut & 0x00ff);
            }
            break;
            case NXP_ChangeEAS: {
                para = new byte[7];
                ret = new byte[7];
                NXPChangeEASPara CustomPara2 = (NXPChangeEASPara) CustomPara;
                System.arraycopy(CustomPara2.AccessPwd, 0, para, 0, 4);
                para[4] = (byte) CustomPara2.isSet;
                para[5] = (byte) ((CustomPara2.TimeOut & 0xff00) >> 8);
                para[6] = (byte) (CustomPara2.TimeOut & 0x00ff);
            }
            break;
            case NXP_EASAlarm: {
                para = new byte[5];
                ret = new byte[5];
                NXPEASAlarmPara CustomPara2 = (NXPEASAlarmPara) CustomPara;
                para[0] = CustomPara2.DR;
                para[1] = CustomPara2.MC = 11;
                para[2] = CustomPara2.TrExt;
                para[3] = (byte) ((CustomPara2.TimeOut & 0xff00) >> 8);
                para[4] = (byte) (CustomPara2.TimeOut & 0x00ff);

            }
            break;
        }
        int re = JnaModuleAPI.INSTANCE.CustomCmd_BaseType(hReader[0], ant, cmdtype.ordinal(), para, ret);
        READER_ERR ERR = READER_ERR.valueOf(re);

        return ERR;
    }

    @Override
    public READER_ERR CustomCmd_BaseType(int ant, int cmdtype, byte[] CustomPara, byte[] CustomRet) {
        int re = JnaModuleAPI.INSTANCE.CustomCmd_BaseType(hReader[0], ant, cmdtype, CustomPara, CustomRet);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR SetGPO(int gpoid, int val) {
        int re = JnaModuleAPI.INSTANCE.SetGPO(hReader[0], gpoid, val);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR GetGPI(int gpoid, int[] val) {
        int re = JnaModuleAPI.INSTANCE.GetGPI(hReader[0], gpoid, val);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR PsamTransceiver(int soltid, int coslen, byte[] cos,
                                             int[] cosresplen, byte[] cosresp, byte[] errcode, short timeout) {
        int re = JnaModuleAPI.INSTANCE.PsamTransceiver(hReader[0], soltid, coslen, cos, cosresplen,
                cosresp, errcode, timeout);
        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR ParamGet(Mtr_Param key, Object val) {
        int re;
        switch (key) {
            case MTR_PARAM_RF_ANTPOWER: {
                byte[] data = new byte[81];
                re = JnaModuleAPI.INSTANCE.ParamGet(hReader[0], key.value(), data);
                if (re == 0) {
                    ((AntPowerConf) val).antcnt = data[0];
                    for (int i = 0; i < data[0]; i++) {
                        AntPower apcf = new AntPower();
                        apcf.antid = data[i * 5 + 1];
                        apcf.readPower = (short) ((data[i * 5 + 2] << 8) | data[i * 5 + 3] & 0xff);
                        apcf.writePower = (short) ((data[i * 5 + 4] << 8) | data[i * 5 + 5] & 0xff);
                        ((AntPowerConf) val).Powers[i] = apcf;
                    }
                }
            }
            break;
            case MTR_PARAM_TAG_FILTER: {
                byte[] data = new byte[266];
                re = JnaModuleAPI.INSTANCE.ParamGet(hReader[0], key.value(), data);
                if (re == 0) {
                    ((TagFilter_ST) val).bank = data[0];
                    ((TagFilter_ST) val).startaddr = ((data[1] & 0xff) << 24) | (data[2] << 16) | (data[3] << 8) | data[4] & 0xff;
                    ((TagFilter_ST) val).flen = (data[5] << 24) | (data[6] << 16) | (data[7] << 8) | data[8] & 0xff;
                    System.arraycopy(data, 9, ((TagFilter_ST) val).fdata, 0, ((TagFilter_ST) val).flen);
                    ((TagFilter_ST) val).isInvert = data[9 + ((TagFilter_ST) val).flen];
                }
            }
            break;
            case MTR_PARAM_TAG_EMBEDEDDATA: {
                byte[] data = new byte[14];
                re = JnaModuleAPI.INSTANCE.ParamGet(hReader[0], key.value(), data);
                if (re == 0) {
                    ((EmbededData_ST) val).bank = data[1];
                    ((EmbededData_ST) val).startaddr = (data[2] << 24) | (data[3] << 16) | (data[4] << 8) | data[5] & 0xff;
                    ((EmbededData_ST) val).bytecnt = (data[6] << 24) | (data[7] << 16) | (data[8] << 8) | data[9] & 0xff;
                    if (data[0] == 14)
                        System.arraycopy(data, 10, ((EmbededData_ST) val).accesspwd, 0, 4);

                }
            }
            break;
            case MTR_PARAM_TAG_INVPOTL: {
                byte[] data = new byte[31];
                re = JnaModuleAPI.INSTANCE.ParamGet(hReader[0], key.value(), data);
                if (re == 0) {
                    ((Inv_Potls_ST) val).potlcnt = data[0];
                    ((Inv_Potls_ST) val).potls = new Inv_Potl[data[0]];
                    for (int i = 0; i < data[0]; i++) {
                        ((Inv_Potls_ST) val).potls[i] = new Inv_Potl();
                        ((Inv_Potls_ST) val).potls[i].potl = SL_TagProtocol.valueOf(data[i * 5 + 1]);
                        ((Inv_Potls_ST) val).potls[i].weight = (data[i * 5 + 1] << 24) | (data[i * 5 + 2] << 16) | (data[i * 5 + 3] << 8) | data[i * 5 + 4] & 0xff;

                    }
                }
            }
            break;
            case MTR_PARAM_READER_CONN_ANTS: {
                byte[] data = new byte[17];
                re = JnaModuleAPI.INSTANCE.ParamGet(hReader[0], key.value(), data);
                if (re == 0) {
                    ((ConnAnts_ST) val).antcnt = data[0];
                    for (int i = 0; i < data[0]; i++) {
                        ((ConnAnts_ST) val).connectedants[i] = data[i + 1];
                    }
                }
            }
            break;
            case MTR_PARAM_FREQUENCY_REGION: {
                int[] data = new int[1];
                re = JnaModuleAPI.INSTANCE.ParamGet(hReader[0], key.value(), data);
                if (re == 0) {
                    ((Region_Conf[]) val)[0] = Region_Conf.valueOf(data[0]);
                    //(val)=Region_Conf.valueOf(data[0]);
                }
            }
            break;
            case MTR_PARAM_FREQUENCY_HOPTABLE: {
                byte[] data = new byte[401];
                re = JnaModuleAPI.INSTANCE.ParamGet(hReader[0], key.value(), data);
                if (re == 0) {
                    ((HoptableData_ST) val).lenhtb = data[0];

                    for (int i = 0; i < data[0]; i++) {
						/*System.out.println("24:"+data[i*4+1]);
						System.out.println("16:"+data[i*4+2]);
						System.out.println("8:"+data[i*4+3]);
						System.out.println("0:"+data[i*4+4]);
						*/
                        ((HoptableData_ST) val).htb[i] = ((data[i * 4 + 1] & 0xff) << 24) |
                                ((data[i * 4 + 2] & 0xff) << 16) | ((data[i * 4 + 3] & 0xff) << 8) | data[i * 4 + 4] & 0xff;
                        //((HoptableData_ST)val).htb[i]=(int)((data[i*5+1]<<24)&0xffffffff|
                        //		(data[i*5+2]<<16)&0xffffff|(data[i*5+3]<<8)&0xffff|data[i*5+4]&0xff);
                    }

                }
            }
            break;
            case MTR_PARAM_TAG_EMDSECUREREAD: {
                int[] data = new int[8];
                re = JnaModuleAPI.INSTANCE.ParamGet(hReader[0], key.value(), data);
                if (re == 0) {
                    ((EmbededSecureRead_ST) val).tagtype = data[0];
                    ((EmbededSecureRead_ST) val).pwdtype = data[1];
                    ((EmbededSecureRead_ST) val).ApIndexStartBitsInEpc = data[2];
                    ((EmbededSecureRead_ST) val).ApIndexBitsNumInEpc = data[3];
                    ((EmbededSecureRead_ST) val).bank = data[4];
                    ((EmbededSecureRead_ST) val).address = data[5];
                    ((EmbededSecureRead_ST) val).blkcnt = data[6];
                    ((EmbededSecureRead_ST) val).accesspwd = data[7];

                }
            }
            break;
            case MTR_PARAM_READER_IP: {
                byte[] data = new byte[48];
                re = JnaModuleAPI.INSTANCE.ParamGet(hReader[0], key.value(), data);
                if (re == 0) {
                    ((Reader_Ip) val).ip = new byte[data[0]];
                    ((Reader_Ip) val).mask = new byte[data[1]];
                    ((Reader_Ip) val).gateway = new byte[data[2]];
                    //System.arraycopy(((Reader_Ip)val).ip,0,data,3,data[0]);
                    //源数组在前，目标数据在后
                    System.arraycopy(data, 3, ((Reader_Ip) val).ip, 0, data[0]);
                    System.arraycopy(data, 3 + data[0], ((Reader_Ip) val).mask, 0, data[1]);
                    System.arraycopy(data, 3 + data[0] + data[1], ((Reader_Ip) val).gateway, 0, data[2]);
                }
            }
            break;
            default:
                re = JnaModuleAPI.INSTANCE.ParamGet(hReader[0], key.value(), val);
                break;
        }

        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

    @Override
    public READER_ERR ParamSet(Mtr_Param key, Object val) {
        int re;
        switch (key) {
            case MTR_PARAM_RF_ANTPOWER: {
                byte[] data = new byte[81];

                data[0] = (byte) ((AntPowerConf) val).antcnt;
                for (int i = 0; i < data[0]; i++) {
                    data[i * 5 + 1] = (byte) ((AntPowerConf) val).Powers[i].antid;
                    data[i * 5 + 2] = (byte) ((((AntPowerConf) val).Powers[i].readPower & 0xff00) >> 8);
                    data[i * 5 + 3] = (byte) (((AntPowerConf) val).Powers[i].readPower & 0x00ff);
                    data[i * 5 + 4] = (byte) ((((AntPowerConf) val).Powers[i].writePower & 0xff00) >> 8);
                    data[i * 5 + 5] = (byte) (((AntPowerConf) val).Powers[i].writePower & 0x00ff);
                }
                re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), data);
            }
            break;
            case MTR_PARAM_TAG_FILTER: {
                if (val != null) {
                    byte[] data = new byte[266];
                    data[0] = (byte) ((TagFilter_ST) val).bank;
                    data[1] = (byte) ((((TagFilter_ST) val).startaddr & 0xff000000) >> 24);
                    data[2] = (byte) ((((TagFilter_ST) val).startaddr & 0x00ff0000) >> 16);
                    data[3] = (byte) ((((TagFilter_ST) val).startaddr & 0x0000ff00) >> 8);
                    data[4] = (byte) (((TagFilter_ST) val).startaddr & 0x000000ff);

                    data[5] = (byte) ((((TagFilter_ST) val).flen & 0xff000000) >> 24);
                    data[6] = (byte) ((((TagFilter_ST) val).flen & 0x00ff0000) >> 16);
                    data[7] = (byte) ((((TagFilter_ST) val).flen & 0x0000ff00) >> 8);
                    data[8] = (byte) (((TagFilter_ST) val).flen & 0x000000ff);

                    System.arraycopy(((TagFilter_ST) val).fdata, 0, data, 9, ((TagFilter_ST) val).flen);
                    data[9 + ((TagFilter_ST) val).flen] = (byte) ((TagFilter_ST) val).isInvert;
                    re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), data);
                } else {
                    re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), null);
                }
            }
            break;
            case MTR_PARAM_TAG_EMBEDEDDATA: {
                if (val != null) {
                    byte[] data = new byte[14];
                    if (((EmbededData_ST) val).accesspwd == null)
                        data[0] = 10;
                    else
                        data[0] = 14;
                    data[1] = (byte) ((EmbededData_ST) val).bank;
                    data[2] = (byte) ((((EmbededData_ST) val).startaddr & 0xff000000) >> 24);
                    data[3] = (byte) ((((EmbededData_ST) val).startaddr & 0x00ff0000) >> 16);
                    data[4] = (byte) ((((EmbededData_ST) val).startaddr & 0x0000ff00) >> 8);
                    data[5] = (byte) (((EmbededData_ST) val).startaddr & 0x000000ff);

                    data[6] = (byte) ((((EmbededData_ST) val).bytecnt & 0xff000000) >> 24);
                    data[7] = (byte) ((((EmbededData_ST) val).bytecnt & 0x00ff0000) >> 16);
                    data[8] = (byte) ((((EmbededData_ST) val).bytecnt & 0x0000ff00) >> 8);
                    data[9] = (byte) (((EmbededData_ST) val).bytecnt & 0x000000ff);
                    if (((EmbededData_ST) val).accesspwd != null)
                        System.arraycopy(((EmbededData_ST) val).accesspwd, 0, data, 10, 4);
                    re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), data);
                } else {
                    re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), null);
                }
            }
            break;
            case MTR_PARAM_TAG_INVPOTL: {
                byte[] data = new byte[31];

                data[0] = (byte) ((Inv_Potls_ST) val).potlcnt;
                for (int i = 0; i < data[0]; i++) {
                    data[i * 5 + 1] = (byte) ((Inv_Potls_ST) val).potls[i].potl.value();
                    data[i * 5 + 2] = (byte) ((((Inv_Potls_ST) val).potls[i].weight & 0xff000000) >> 24);
                    data[i * 5 + 3] = (byte) ((((Inv_Potls_ST) val).potls[i].weight & 0x00ff0000) >> 16);
                    data[i * 5 + 4] = (byte) ((((Inv_Potls_ST) val).potls[i].weight & 0x0000ff00) >> 8);
                    data[i * 5 + 5] = (byte) (((Inv_Potls_ST) val).potls[i].weight & 0x000000ff);

                }
                re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), data);
            }
            break;
            case MTR_PARAM_FREQUENCY_REGION: {
                byte[] data = new byte[1];
                data[0] = (byte) ((Region_Conf) val).ordinal();
                re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), data);
            }
            break;
            case MTR_PARAM_FREQUENCY_HOPTABLE: {
                byte[] data = new byte[((HoptableData_ST) val).lenhtb * 4 + 1];
                data[0] = (byte) ((HoptableData_ST) val).lenhtb;

                for (int i = 0; i < data[0]; i++) {
                    data[i * 4 + 1] = (byte) ((((HoptableData_ST) val).htb[i] & 0xff000000) >> 24);
                    data[i * 4 + 2] = (byte) ((((HoptableData_ST) val).htb[i] & 0x00ff0000) >> 16);
                    data[i * 4 + 3] = (byte) ((((HoptableData_ST) val).htb[i] & 0x0000ff00) >> 8);
                    data[i * 4 + 4] = (byte) (((HoptableData_ST) val).htb[i] & 0x000000ff);
                }
                re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), data);
            }
            break;
            case MTR_PARAM_TAG_EMDSECUREREAD: {
                if (val == null) {
                    re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), null);
                } else {
                    int[] data = new int[8];
                    data[0] = ((EmbededSecureRead_ST) val).tagtype;
                    data[1] = ((EmbededSecureRead_ST) val).pwdtype;
                    data[2] = ((EmbededSecureRead_ST) val).ApIndexStartBitsInEpc;
                    data[3] = ((EmbededSecureRead_ST) val).ApIndexBitsNumInEpc;
                    data[4] = ((EmbededSecureRead_ST) val).bank;
                    data[5] = ((EmbededSecureRead_ST) val).address;
                    data[6] = ((EmbededSecureRead_ST) val).blkcnt;
                    data[7] = ((EmbededSecureRead_ST) val).accesspwd;
                    re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), data);
                }
            }
            break;
            case MTR_PARAM_READER_IP: {
                //data[0] ip length data[1] mask length data[2] gateway length data[3]... ip  data[3+data[0]]... mask...

                int ipl = ((Reader_Ip) val).ip.length;
                int ml = ((Reader_Ip) val).mask.length;
                int gl = ((Reader_Ip) val).gateway.length;
                byte[] data = new byte[3 + ipl + ml + gl];
                data[0] = (byte) ipl;
                data[1] = (byte) ml;
                data[2] = (byte) gl;
                System.arraycopy(((Reader_Ip) val).ip, 0, data, 3, ipl);
                System.arraycopy(((Reader_Ip) val).mask, 0, data, 3 + ipl, ml);
                System.arraycopy(((Reader_Ip) val).ip, 0, data, 3 + ipl + ml, gl);
                re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), data);
            }
            break;
            default:
                re = JnaModuleAPI.INSTANCE.ParamSet(hReader[0], key.value(), val);
                break;
        }

        READER_ERR ERR = READER_ERR.valueOf(re);
        return ERR;
    }

}
