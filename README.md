# XML Mapper Apache Spark SQL

Java Version 11

**Note**: To run application run Application.java file. Also you must set below JVM arguments to run application.

```
--add-opens=java.base/jdk.internal.ref=ALL-UNNAMED
--add-exports=java.base/jdk.internal.ref=ALL-UNNAMED 

--add-opens=java.base/sun.nio.ch=ALL-UNNAMED
--add-exports=java.base/sun.nio.ch=ALL-UNNAMED

--add-opens=java.base/sun.nio.ch=ALL-UNNAMED 
--add-exports=java.base/sun.nio.ch=ALL-UNNAMED 

--add-opens=java.base/java.lang=ALL-UNNAMED
--add-exports=java.base/java.lang=ALL-UNNAMED

--add-opens=java.base/java.lang.reflect=ALL-UNNAMED
--add-exports=java.base/java.lang.reflect=ALL-UNNAMED

--add-opens=java.base/java.io=ALL-UNNAMED 
--add-exports=java.base/java.io=ALL-UNNAMED 

--add-opens=jdk.unsupported/sun.misc=ALL-UNNAMED 
--add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED 

--add-opens=java.base/sun.nio.cs=ALL-UNNAMED 
--add-exports=java.base/sun.nio.cs=ALL-UNNAMED

--add-opens=java.base/sun.util.calendar=ALL-UNNAMED 
--add-exports=java.base/sun.util.calendar=ALL-UNNAMED
```

# ISO20022 Message Sample Data URI

https://developer.ibm.com/articles/dm-1307isopayment/

# Output

```
+---------+--------------------+------------+------------+--------------------+-----------+------+------------+
|BtchBookg|                Cdtr|    CdtrAcct|     CdtrAgt|        DrctDbtTxInf|   PmtInfId|PmtMtd|ReqdColltnDt|
+---------+--------------------+------------+------------+--------------------+-----------+------+------------+
|    false|{Smith, {3, GB, W...|{{{789123}}}|{{AAAAUS29}}|{SHAR, {Lee, {45,...|CAVAY/88683|    DD|  2021-05-29|
+---------+--------------------+------------+------------+--------------------+-----------+------+------------+

Index field 0 is false
Index field 1 is {
  "Nm" : "Smith",
  "PstlAdr" : {
    "BldgNb" : 3,
    "Ctry" : "GB",
    "PstCd" : "W12 8QT",
    "StrtNm" : "Virginia Lane",
    "TwnNm" : "London"
  }
}
Index field 2 is {
  "Id" : {
    "Othr" : {
      "Id" : 789123
    }
  }
}
Index field 3 is {
  "FinInstnId" : {
    "BICFI" : "AAAAUS29"
  }
}
Index field 4 is {
  "ChrgBr" : "SHAR",
  "Dbtr" : {
    "Nm" : "Lee",
    "PstlAdr" : {
      "BldgNb" : 45,
      "Ctry" : "GB",
      "PstCd" : "E56 7HY",
      "StrtNm" : "Cross Road",
      "TwnNm" : "London"
    }
  },
  "DbtrAcct" : {
    "Id" : {
      "Othr" : {
        "Id" : 789101
      }
    }
  },
  "DbtrAgt" : {
    "FinInstnId" : {
      "BICFI" : "CCCCUS27"
    }
  },
  "DrctDbtTx" : {
    "PreNtfctnDt" : "2021-05-29",
    "PreNtfctnId" : "Smith2435/2013"
  },
  "InstdAmt" : {
    "_Ccy" : "GBP",
    "_VALUE" : 985
  },
  "PmtId" : {
    "EndToEndId" : "AY090327/456"
  },
  "PmtTpInf" : {
    "InstrPrty" : "NORM",
    "SeqTp" : "OOFF",
    "SvcLvl" : {
      "Prtry" : "VERPA‑1"
    }
  },
  "RmtInf" : {
    "Ustrd" : "CAR INSURANCE PREMIUM"
  }
}
Index field 5 is CAVAY/88683
Index field 6 is DD
Index field 7 is 2021-05-29
```