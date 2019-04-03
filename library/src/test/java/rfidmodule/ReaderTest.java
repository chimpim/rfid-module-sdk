package rfidmodule;

import com.chimpim.rfidmodule.Reader;
import com.chimpim.rfidmodule.RfidModule;
import com.chimpim.rfidmodule.internal.HexStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReaderTest {

    private Reader reader;

    private final String src = "192.168.1.100";
    private final int rtype = 4;
    private Reader.READER_ERR err;


    @Before
    public void setup() {
        reader = RfidModule.newJniReader();
        err = reader.InitReader_Notype(src, rtype);
        System.out.println("InitReader_Notype Err: " + err);
    }

    @After
    public void shutdown() {
        reader.CloseReader();
    }

    @Test
    public void TagInventory() {
        if (err == Reader.READER_ERR.MT_OK_ERR) {
            for (int j = 0; j < 10; j++) {
                Reader.TAGINFO[] tagInfos = new Reader.TAGINFO[200];
                int[] tagCnt = new int[1];
                short timeout = 100;
                int[] ants = new int[]{1}; //, 2, 3, 4};
                int antCnt = ants.length;
                err = reader.TagInventory(ants, antCnt, timeout, tagInfos, tagCnt);
                System.out.println("TagInventory Err: " + err);
                if (err == Reader.READER_ERR.MT_OK_ERR) {
                    for (int i = 0; i < tagCnt[0]; i++) {
                        System.out.println(HexStringUtils.bytesToHexString(tagInfos[i].EpcId));
                    }
                }
            }
        }
    }
}