package com.evernym.sdk.vcx;

import com.evernym.sdk.vcx.utils.UtilsApi;
import com.evernym.sdk.vcx.vcx.VcxApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

public class VcxUtilsTest {
    @BeforeEach
    void setup() throws Exception {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");
        if (!TestHelper.vcxInitialized) {
            VcxApi.vcxInitCore(TestHelper.VCX_CONFIG_TEST_MODE);
            TestHelper.vcxInitialized = true;
        }
    }

    @Test
    @DisplayName("get ledger author agreement")
    void vcxGetLedgerAuthorAgreement() throws VcxException, ExecutionException, InterruptedException {
        String agreement = TestHelper.getResultFromFuture(UtilsApi.getLedgerAuthorAgreement());
        assert (agreement.equals("{\"text\":\"Default indy agreement\", \"version\":\"1.0.0\", \"aml\": {\"acceptance mechanism label1\": \"description\"}}"));
    }

    @Test
    @DisplayName("set active txn author agreement meta")
    void vcxSetActiveTxnAuthorAgreementMeta() throws VcxException {
        UtilsApi.setActiveTxnAuthorAgreementMeta("indy agreement", "1.0.0", null,
                "acceptance type 1", 123456789);
    }

    @Test
    @DisplayName("endorse transaction")
    void vcxEndorseTransaction() throws VcxException, ExecutionException, InterruptedException {
        String transactionJson = "{\"req_id\":1, \"identifier\": \"EbP4aYNeTHL6q385GuVpRV\", \"signature\": \"gkVDhwe2\", \"endorser\": \"NcYxiDXkpYi6ov5FcYDi1e\"}";
        TestHelper.getResultFromFuture(UtilsApi.vcxEndorseTransaction(transactionJson));
    }
}
