package com.swissborg.swissborgtask.common.core

object Constants {

    const val API_BASE_URL = "https://api-pub.bitfinex.com/"

    const val REFRESH_RATE = 5000L

    const val SWISS_BORG_DATABASE_NAME = "swiss_borg_db"

    const val GET_TICKERS_QUERY =
        "tBTCUSD,tETHUSD,tCHSB:USD,tLTCUSD,tXRPUSD,tDSHUSD,tRRTUSD,tEOSUSD,tSANUSD,tDATUSD,tSNTUSD,tDOGE:USD,tLUNA:USD,tMATIC:USD,tNEXO:USD,tOCEAN:USD,tBEST:USD,tAAVE:USD,tPLUUSD,tFILUSD"
    const val GET_FRIENDLY_NAME_PATH = "label"
    const val GET_API_SYMBOL_PATH = "sym"
}
