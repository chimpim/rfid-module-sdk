#ifndef MODULE_M5E_FAMILY_READER_
#define MODULE_M5E_FAMILY_READER_

#define SDK_HEADER_FILE_VERSION 20160921

#if defined(_WIN32_WCE)
#define EXTERN __declspec(dllexport)
#elif defined(WIN32)
#define EXTERN __stdcall
#else
#define EXTERN
#endif

#ifdef __cplusplus
extern "C"
{
#endif

	typedef enum
	{
		MT_OK_ERR = 0,
		MT_IO_ERR = 1,
		MT_INTERNAL_DEV_ERR = 2,
		MT_CMD_FAILED_ERR = 3,
		MT_CMD_NO_TAG_ERR = 4,
		MT_M5E_FATAL_ERR = 5,
		MT_OP_NOT_SUPPORTED = 6,
		MT_INVALID_PARA = 7,
		MT_INVALID_READER_HANDLE = 8,
		MT_HARDWARE_ALERT_ERR_BY_HIGN_RETURN_LOSS = 9,
		MT_HARDWARE_ALERT_ERR_BY_TOO_MANY_RESET = 10,
		MT_HARDWARE_ALERT_ERR_BY_NO_ANTENNAS = 11,
		MT_HARDWARE_ALERT_ERR_BY_HIGH_TEMPERATURE = 12,
		MT_HARDWARE_ALERT_ERR_BY_READER_DOWN = 13,
		MT_HARDWARE_ALERT_ERR_BY_UNKNOWN_ERR = 14,
		M6E_INIT_FAILED = 15,
		MT_OP_EXECING = 16,
		MT_UNKNOWN_READER_TYPE = 17,
		MT_OP_INVALID = 18,
		MT_HARDWARE_ALERT_BY_FAILED_RESET_MODLUE = 19,
		MT_MAX_ERR_NUM = 20,
		MT_TEST_DEV_FAULT_1 = 51,
		MT_TEST_DEV_FAULT_2 = 52,
		MT_TEST_DEV_FAULT_3 = 53,
		MT_TEST_DEV_FAULT_4 = 54,
		MT_TEST_DEV_FAULT_5 = 55,
		MT_MAX_INT_NUM = 0xfffffff2,
	} READER_ERR;

	typedef enum
	{
		MODOULE_NONE,
		MODOULE_R902_M1S,
		MODOULE_R902_M2S,
		MODOULE_M5E,
		MODOULE_M5E_C,
		MODOULE_M6E,
		MODOULE_PR9000,
		MODOULE_M5E_PRC,
		MODOULE_M6E_PRC,
		MODOULE_M6E_MICRO,
		MODOULE_SLR1100,
		MODOULE_SLR1200,
		MODOULE_SLR1300,
		MODOULE_SLR3000,
		MODOULE_SLR5100,
		MODOULE_SLR5200,
		MODOULE_SLR3100,
		MODOULE_SLR3200,
		MODOULE_SLR5300,
	} Module_Type;

	typedef enum
	{
		MAINBOARD_NONE,
		MAINBOARD_ARM7,
		MAINBOARD_SERIAL,
		MAINBOARD_WIFI,
		MAINBOARD_ARM9,
		MAINBOARD_ARM9_WIFI,
		MAINBOARD_INTEGRATED_SERIAL,
		MAINBOARD_ARM9_V2,
		MAINBOARD_ARM9_WIFI_V2,
	} MaindBoard_Type;

	typedef enum
	{
		MODULE_TWO_ANTS,
		MODULE_FOUR_ANTS,
		MODULE_THREE_ANTS,
		MODULE_ONE_ANT,
		PR9000,
		MODULE_ARM7_TWO_ANTS,
		MODULE_ARM7_FOUR_ANTS,
		M6E_ARM7_FOUR_ANTS,
		M56_ARM7_FOUR_ANTS,
		R902_M1S,
		R902_M2S,
		ARM7_16ANTS,
		SL_COMMN_READER,
	} Reader_Type;

	typedef struct
	{
		Module_Type module;
		MaindBoard_Type board;
		Reader_Type logictype;
	} HardwareDetails;

	typedef enum
	{
		SL_TAG_PROTOCOL_NONE = 0x00,
		SL_TAG_PROTOCOL_ISO180006B = 0x03,
		SL_TAG_PROTOCOL_GEN2 = 0x05,
		SL_TAG_PROTOCOL_ISO180006B_UCODE = 0x06,
		SL_TAG_PROTOCOL_IPX64 = 0x07,
		SL_TAG_PROTOCOL_IPX256 = 0x08,
	} SL_TagProtocol;

	typedef enum
	{
		RG_NONE = 0x0,
		RG_NA = 0x01,
		RG_EU = 0x02,
		RG_EU2 = 0X07,
		RG_EU3 = 0x08,
		RG_KR = 0x03,
		RG_PRC = 0x06,
		RG_PRC2 = 0x0A,
		RG_OPEN = 0xFF,
	} Region_Conf;

	typedef enum
	{

		MTR_PARAM_POTL_GEN2_SESSION,
		MTR_PARAM_POTL_GEN2_Q,
		MTR_PARAM_POTL_GEN2_TAGENCODING,
		MTR_PARAM_POTL_GEN2_MAXEPCLEN,
		MTR_PARAM_RF_ANTPOWER,
		MTR_PARAM_RF_MAXPOWER,
		MTR_PARAM_RF_MINPOWER,
		MTR_PARAM_TAG_FILTER,
		MTR_PARAM_TAG_EMBEDEDDATA,
		MTR_PARAM_TAG_INVPOTL,
		MTR_PARAM_READER_CONN_ANTS,
		MTR_PARAM_READER_AVAILABLE_ANTPORTS,
		MTR_PARAM_READER_IS_CHK_ANT,
		MTR_PARAM_READER_VERSION,
		MTR_PARAM_READER_IP,
		MTR_PARAM_FREQUENCY_REGION,
		MTR_PARAM_FREQUENCY_HOPTABLE,
		MTR_PARAM_POTL_GEN2_BLF,
		MTR_PARAM_POTL_GEN2_WRITEMODE,

		MTR_PARAM_POTL_GEN2_TARGET, //0:A; 1:B; 2:A->B; 3:B->A
		MTR_PARAM_TAGDATA_UNIQUEBYANT,
		MTR_PARAM_TAGDATA_UNIQUEBYEMDDATA,
		MTR_PARAM_TAGDATA_RECORDHIGHESTRSSI,
		MTR_PARAM_RF_TEMPERATURE,
		MTR_PARAM_RF_HOPTIME,
		MTR_PARAM_RF_LBT_ENABLE,
		MTR_PARAM_RF_SUPPORTEDREGIONS,
		MTR_PARAM_POTL_SUPPORTEDPROTOCOLS,
		MTR_PARAM_POTL_ISO180006B_BLF,
		MTR_PARAM_POTL_GEN2_TARI, //0:Tari of 25 microseconds;1:Tari of 12.5 microseconds;2:Tari of 6.25 microseconds
		MTR_PARAM_TRANS_TIMEOUT,
		MTR_PARAM_TAG_EMDSECUREREAD,
		MTR_PARAM_TRANSMIT_MODE,
		MTR_PARAM_POWERSAVE_MODE,
		MTR_PARAM_TAG_SEARCH_MODE,

		MTR_PARAM_POTL_ISO180006B_MODULATION_DEPTH,
		MTR_PARAM_POTL_ISO180006B_DELIMITER,
		MTR_PARAM_RF_ANTPORTS_VSWR,

		//alter by dkg
		MTR_PARAM_SAVECONFIGURATION,
		MTR_PARAM_MAXINDEX,

		//by dkg
		MTR_PARAM_INTERNALSAVECONFIGURATION,

	} Mtr_Param;

	typedef enum
	{
		TAGOP_EMBEDDEDSPEC_NONE = 0,
		TAGOP_MONZA4_QTPEEK2PRIVATE = 1,
		TAGOP_MONZA4_QTPEEK2PUBLIC = 2,
	} TagOpEmbeddedSpecReq_Code;

#define MAXANTCNT 16
#define MAXIPSTRLEN 50
#define HOPTABLECNT 100
#define MAXEPCBYTESCNT 62
#define MAXEMBDATALEN 128
#define MAXINVPOTLSCNT 6
#define MAXGPICNT 8

	typedef struct
	{
		int GpiId;
		int State;
	} GpiState_ST;
	typedef struct
	{
		int gpiCount;
		GpiState_ST gpiStats[MAXGPICNT];
	} GpiInfo_ST;

	typedef struct
	{
		unsigned int ReadCnt;
		int RSSI;
		unsigned char AntennaID;
		unsigned int Frequency;
		unsigned int TimeStamp;
		unsigned short EmbededDatalen;
		unsigned char EmbededData[MAXEMBDATALEN];
		unsigned char Res[2];
		unsigned short Epclen;
		unsigned char PC[2];
		unsigned char CRC[2];
		unsigned char EpcId[MAXEPCBYTESCNT];
		int Phase;
		SL_TagProtocol protocol;
	} TAGINFO;

	typedef struct
	{
		int tagtype;
		int pwdtype;
		int ApIndexStartBitsInEpc;
		int ApIndexBitsNumInEpc;
		int bank;
		int address;
		int blkcnt;
		unsigned int accesspwd;
	} EmbededSecureRead_ST;

	typedef struct
	{
		char ip[50];
		char mask[50];
		char gateway[50];
	} Reader_Ip;

	typedef struct
	{
		char ver[20];
	} Reader_Ver;

	typedef struct
	{
		int bank;
		unsigned int startaddr;
		int flen;
		unsigned char *fdata;
		int isInvert;
	} TagFilter_ST;

	typedef struct
	{
		int bank;
		int startaddr;
		int bytecnt;
		unsigned char *accesspwd;
	} EmbededData_ST;

	typedef struct
	{
		int antid;
		unsigned short readPower;
		unsigned short writePower;
	} AntPower;

	typedef struct
	{
		int antcnt;
		AntPower Powers[MAXANTCNT];
	} AntPowerConf;

	typedef struct
	{
		int andid;
		float vswr;
	} AntLinkVSWR;

	typedef struct
	{
		int antcnt;
		AntLinkVSWR AntVSWRS[MAXANTCNT];
	} AntPortsVSWR;

	typedef struct
	{
		int antcnt;
		int connectedants[MAXANTCNT];
	} ConnAnts_ST;

	typedef struct
	{
		unsigned int htb[HOPTABLECNT];
		int lenhtb;
	} HoptableData_ST;

	typedef struct
	{
		SL_TagProtocol potl;
		int weight;
	} Inv_Potl;

	typedef struct
	{
		int potlcnt;
		Inv_Potl potls[MAXINVPOTLSCNT];
	} Inv_Potls_ST;

	int EXTERN GetSDKVersion(void);

#if !defined(__SCM__)
	READER_ERR EXTERN GetHardwareDetails(int hReader, HardwareDetails *phd);

	READER_ERR EXTERN InitReader_Notype(int *hReader, char *src, int antscnt);

#if defined(WIN32) && (!defined(_WIN32_WCE))
	READER_ERR EXTERN InitReader_Notype_Dg(int *hReader, char *src, int antscnt);
#endif

#endif

	READER_ERR EXTERN InitReader(int *hReader, char *src, Reader_Type rtype);

	void EXTERN CloseReader(int hReader);

	READER_ERR EXTERN GetTagData(int hReader, int ant,
								 unsigned char bank, unsigned int address,
								 int blkcnt, unsigned char *data,
								 unsigned char *accesspasswd, unsigned short timeout);

	READER_ERR EXTERN GetTagDataEx(int hReader, int ant,
								   unsigned char bank, unsigned int address,
								   int blkcnt, unsigned char *data,
								   unsigned char *accesspasswd, unsigned short timeout,
								   int option, void *pSpec);

	READER_ERR EXTERN WriteTagData(int hReader, int ant,
								   unsigned char bank, unsigned int address,
								   unsigned char *data, int datalen,
								   unsigned char *accesspasswd, unsigned short timeout);

	READER_ERR EXTERN WriteTagEpcEx(int hReader, int ant, unsigned char *Epc,
									int epclen, unsigned char *accesspwd, unsigned short timeout);

	READER_ERR EXTERN TagInventory_Count(int hReader, int *ants, int antcnt, unsigned short timeout,
										 int *tagcnt, int isclearbuf);

	READER_ERR EXTERN TagInventory(int hReader, int *ants, int antcnt, unsigned short timeout,
								   TAGINFO *pTInfo, int *tagcnt);

	READER_ERR EXTERN TagInventory_Raw(int hReader, int *ants, int antcnt, unsigned short timeout,
									   int *tagcnt);

	READER_ERR EXTERN TagInventory_BaseType(int hReader, int *ants, int antcnt, unsigned short timeout,
											unsigned char *outbuf, int *tagcnt);

	READER_ERR EXTERN GetNextTag(int hReader, TAGINFO *pTInfo);

	READER_ERR EXTERN GetNextTag_BaseType(int hReader, unsigned char *outbuf);

	READER_ERR EXTERN PsamTransceiver(int hReader, int soltid, int coslen,
									  unsigned char *cos, int *cosresplen, unsigned char *cosresp,
									  unsigned char *errcode, unsigned short timeout);

#if !defined(__SCM__)
	READER_ERR EXTERN AsyncStartReading(int hReader, int *ants, int antcnt, int option);

	READER_ERR EXTERN AsyncGetTagCount(int hReader, int *tagcnt);

	READER_ERR EXTERN AsyncGetNextTag(int hReader, TAGINFO *pTInfo);

	READER_ERR EXTERN AsyncStopReading(int hReader);
#endif

	typedef enum
	{
		LOCK_OBJECT_KILL_PASSWORD = 0x01, //锁定对象为销毁密码
		LOCK_OBJECT_ACCESS_PASSWD = 0x02, //锁定对象为访问密码
		LOCK_OBJECT_BANK1 = 0x04,		  //锁定对象为bank1
		LOCK_OBJECT_BANK2 = 0x08,		  //锁定对象为bank2
		LOCK_OBJECT_BANK3 = 0x10,		  //锁定对象为bank3
	} Lock_Obj;

	typedef enum
	{
		KILL_PASSWORD_UNLOCK = 0x0000,
		KILL_PASSWORD_LOCK = 0x0200,	  //销毁密码密码锁定
		KILL_PASSWORD_PERM_LOCK = 0x0300, //销毁密码永久锁定

		ACCESS_PASSWD_UNLOCK = 0x00,
		ACCESS_PASSWD_LOCK = 0x80,		//访问密码密码锁定
		ACCESS_PASSWD_PERM_LOCK = 0xC0, //访问密码永久锁定

		BANK1_UNLOCK = 0x00,
		BANK1_LOCK = 0x20,		//bank1密码锁定
		BANK1_PERM_LOCK = 0x30, //bank1永久锁定

		BANK2_UNLOCK = 0x00,
		BANK2_LOCK = 0x08,		//bank2密码锁定
		BANK2_PERM_LOCK = 0x0C, //bank2永久锁定

		BANK3_UNLOCK = 0x00,
		BANK3_LOCK = 0x02,		//bank3密码锁定
		BANK3_PERM_LOCK = 0x03, //bank3永久锁定
	} Lock_Type;

	READER_ERR EXTERN LockTag(int hReader, int ant,
							  unsigned char lockobjects, unsigned short locktypes,
							  unsigned char *accesspasswd, unsigned short timeout);

	READER_ERR EXTERN KillTag(int hReader, int ant,
							  unsigned char *killpasswd,
							  unsigned short timeout);

	READER_ERR EXTERN Lock180006BTag(int hReader, int ant, int startblk,
									 int blkcnt, unsigned short timeout);

	READER_ERR EXTERN BlockPermaLock(int hReader, int ant, int readlock, int startblk,
									 int blkrange, unsigned char *mask, unsigned char *pwd, unsigned short timeout);

	READER_ERR EXTERN BlockErase(int hReader, int ant, int bank, int wordaddr, int wordcnt,
								 unsigned char *pwd, unsigned short timeout);

	typedef enum
	{
		NXP_SetReadProtect,
		NXP_ResetReadProtect,
		NXP_ChangeEAS,
		NXP_EASAlarm,
		NXP_Calibrate,

		ALIEN_Higgs2_PartialLoadImage,
		ALIEN_Higgs2_FullLoadImage,

		ALIEN_Higgs3_FastLoadImage,
		ALIEN_Higgs3_LoadImage,
		ALIEN_Higgs3_BlockReadLock,
		ALIEN_Higgs3_BlockPermaLock,

		IMPINJ_M4_Qt,
	} CustomCmdType;

	typedef struct
	{
		unsigned char AccessPwd[4];
		int CmdType;	 //0 = read the QT control bits in cache;1 = write the QT control bits
		int MemType;	 //0 = Tag uses Private Memory Map;1 = Tag uses Public Memory Map
		int PersistType; //0 = write the QT Control to volatile memory;1 = write the QT Control to nonvolatile memory
		int RangeType;   //0 = Tag does not reduce range;1 = Tag reduces range if in or about to be in OPEN or SECURED state
		unsigned short TimeOut;
	} IMPINJM4QtPara;

	typedef struct
	{
		int MemType;   //0 = Tag uses Private Memory Map;1 = Tag uses Public Memory Map
		int RangeType; //0 = Tag does not reduce range;1 = Tag reduces range if in or about to be in OPEN or SECURED state
	} IMPINJM4QtResult;

	typedef struct
	{
		unsigned char AccessPwd[4];
		int isSet;
		unsigned short TimeOut;
	} NXPChangeEASPara;

	typedef struct
	{
		unsigned char DR;
		unsigned char MC;
		unsigned char TrExt;
		unsigned short TimeOut;
	} NXPEASAlarmPara;

	typedef struct
	{
		unsigned char EASdata[8];
	} NXPEASAlarmResult;

	typedef struct
	{
		unsigned char AccessPwd[4];
		unsigned char BlkBits;
		unsigned short TimeOut;
	} ALIENHiggs3BlockReadLockPara;

#if !defined(__SCM__)
	typedef void (*MT_AuthReqCallback)(const unsigned char *epc, int epclen,
									   void *cookie, unsigned char *accesspwd);

	typedef struct MT_AuthReqCallbackBlock
	{
		MT_AuthReqCallback GetAccessPwdFromEpc;
		void *cookie;
	} MT_AuthReqCallbackBlock;
	READER_ERR EXTERN SetAuthReqCallbackBlock(int hReader, MT_AuthReqCallbackBlock *bok);

	READER_ERR EXTERN StartTagStreaming(int hReader, int *ants, int antcnt, int cycle);

	READER_ERR EXTERN StopTagStreaming(int hReader);

	typedef void (*MT_TagStreamingOnError)(READER_ERR errcode, void *cookie);
	typedef struct MT_TagStreamingOnErrorBlock
	{
		MT_TagStreamingOnError handler;
		void *cookie;
	} MT_TagStreamingOnErrorBlock;
	READER_ERR EXTERN SetTagStreamingOnErrHandler(int hReader, MT_TagStreamingOnErrorBlock *bok);

	typedef void (*MT_TagStreamingOnTag)(TAGINFO *tag, void *cookie);
	typedef struct MT_TagStreamingOnTagBlock
	{
		MT_TagStreamingOnTag handler;
		void *cookie;
	} MT_TagStreamingOnTagBlock;

	READER_ERR EXTERN SetTagStreamingOnTagHandler(int hReader, MT_TagStreamingOnTagBlock *bok);
	/*
	data:要发送的数据
	datalen：要发送数据的长度，单位字节
	timeout：超时时间，单位毫秒
	返回值：0表示成功，-1表示失败
*/
	int EXTERN DataTransportSend(int hReader, unsigned char *data, int datalen, int timeout);

	/*
	data:接收缓冲区
	datalen：要接收数据的长度，单位字节
	timeout：超时时间，单位毫秒
	返回值：0表示成功，-1表示失败
*/
	int EXTERN DataTransportRecv(int hReader, unsigned char *data, int datalen, int timeout);

	READER_ERR EXTERN SaveDataOnReader(int hReader, int address, unsigned char *data, int datalen);

	READER_ERR EXTERN ReadDataOnReader(int hReader, int address, unsigned char *data, int datalen);

	READER_ERR EXTERN EraseDataOnReader(int hReader);
#endif

	READER_ERR EXTERN CustomCmd(int hReader, int ant, CustomCmdType cmdtype, void *CustomPara, void *CustomRet);

	READER_ERR EXTERN CustomCmd_BaseType(int hReader, int ant, int cmdtype, unsigned char *CustomPara, unsigned char *CustomRet);

	READER_ERR EXTERN ParamGet(int hReader, Mtr_Param key, void *val);

	READER_ERR EXTERN ParamSet(int hReader, Mtr_Param key, void *val);

	void EXTERN Hex2Str(unsigned char *buf, int len, char *out);

	void EXTERN Str2Hex(const char *buf, int len, unsigned char *hexbuf);

	void EXTERN Str2Binary(const char *buf, int len, unsigned char *binarybuf);

#if defined(__SCM__)

	typedef struct
	{
		unsigned int seconds;
		unsigned int milisecs;
	} SCM_TIME_ST;

	typedef void SCM_GetSysClock(SCM_TIME_ST *time);
	typedef void SCM_Sleep(int milisecs);
	typedef int SCM_Trans_Recv(unsigned char *data, unsigned int len, unsigned int timeout);
	typedef int SCM_Trans_Send(unsigned char *data, unsigned int len, unsigned int timeout);
	typedef int SCM_Trans_Open();
	typedef int SCM_Trans_Close();
	typedef int SCM_Trans_SetPhySpeed(unsigned int speed);
	typedef int SCM_Trans_ClearRecvBuffer();
	typedef int SCM_Trans_Flush();
	typedef int SCM_Trans_IsFinResp(unsigned char *resp);

	typedef struct
	{
		SCM_GetSysClock *scmGetSysClock;
		SCM_Sleep *scmSleep;
		SCM_Trans_Recv *scmTransRecv;
		SCM_Trans_Send *scmTransSend;
		SCM_Trans_Open *scmTransOpen;
		SCM_Trans_Close *scmTransClose;
		SCM_Trans_SetPhySpeed *scmTransSetPhySpeed;
		SCM_Trans_ClearRecvBuffer *scmTransClearRecvBuffer;
		SCM_Trans_Flush *scmTransFlush;
		SCM_Trans_IsFinResp *scmTransIsFinResp;
	} SCM_READER_CLK_TRANS;

	READER_ERR EXTERN SCMInitEnv(SCM_READER_CLK_TRANS clk_trans);
	READER_ERR EXTERN SCMSetDgPrintf(int pt);
	READER_ERR EXTERN StartTagInventory(int hReader, int *ants, int antcnt, unsigned short timeout);
	READER_ERR EXTERN IsFinTagInventory(int hReader, int *tagcnt);

	READER_ERR EXTERN StartGetTagData(int hReader, int ant,
									  unsigned char bank, unsigned int address,
									  int blkcnt, unsigned char *accesspasswd, unsigned short timeout);
	READER_ERR EXTERN IsFinGetTagData(int hReader, unsigned char *data);

#endif

	/*
    以下这些函数的功能已经被ParamGet和ParamSet所代替，不建议再使用了
    */
	READER_ERR EXTERN WriteTagEpc(int hReader, int ant,
								  unsigned char *Epc, int epclen, unsigned short timeout);

	READER_ERR EXTERN SetInvPotl(int hReader, int potlcnt, SL_TagProtocol *potls, int *pweithts);

	READER_ERR EXTERN SetFilter(int hReader, int bank, unsigned int startaddr,
								unsigned char *filterdata, int datalen, int isInvert);

	READER_ERR EXTERN ResetFilter(int hReader);

	READER_ERR EXTERN SetEmbededData(int hReader, int bank, int startaddr, int bytecnt,
									 unsigned char *accesspwd);

	READER_ERR EXTERN ResetEmbededData(int hReader);

	READER_ERR EXTERN GetAllAnts(int hReader, int *ants, int *antscnt);

	READER_ERR EXTERN GetAntsPower(int hReader, int *antcnt, unsigned short *rpwrs, unsigned short *wpwrs);

	READER_ERR EXTERN GetIpInfo(int hReader, char *ip, char *subnet, char *gateway);

	READER_ERR EXTERN GetGen2Session(int hReader, int *sess);

	READER_ERR EXTERN SetRegion(int hReader, Region_Conf rg);

	READER_ERR EXTERN SetIpInfo(int hReader, char *ip, char *subnet, char *gateway);

	READER_ERR EXTERN SetReadPower(int hReader, unsigned short power);

	READER_ERR EXTERN SetWritePower(int hReader, unsigned short power);

	READER_ERR EXTERN SetEPCLength(int hReader, int len);

	READER_ERR EXTERN SetGPO(int hReader, int gpoid, int state);

	READER_ERR EXTERN GetGPI(int hReader, int gpiid, int *state);

	READER_ERR EXTERN GetGPIEx(int hReader, GpiInfo_ST *gpiinfo);

	READER_ERR EXTERN SetGen2Session(int hReader, int sess);

	READER_ERR EXTERN SetGen2MVal(int hReader, int mval);

	READER_ERR EXTERN SetAntsPower(int hReader, int *ants, int antcnt, unsigned short *rpwrs, unsigned short *wpwrs);

	READER_ERR EXTERN GetPowerLimit(int hReader, unsigned short *MaxPower, unsigned short *MinPower);

#ifdef __cplusplus
}
#endif

#endif
