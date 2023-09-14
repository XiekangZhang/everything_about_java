--------------------------------------------------------
--  DDL for Package OSS_ER_FUNCTION
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE PACKAGE "ER_REPORTING"."OSS_ER_FUNCTION" AS
--  CREATE OR REPLACE PACKAGE OSS_ER_FUNCTION AS
--package fr ER_REPORTING Funktionen
--Thomas Rochlitz
--

--26.10.2022 Thomas Rochlitz: TLND-2315 ER many Temp-Segment problems, recheck the source code, split code
--                            INSERT_ER_SM_DAILY_SO_DATA -> INSERT_ER_SM_DAILY_SO_DATA_2
--                            UPDATE_ER_SM_DAILY_SO_DATA -> UPDATE_ER_SM_DAILY_SO_DATA_2
--                            new UPDATE_ER_SM_DAILY_GENERAL
--18.10.2022 Thomas Rochlitz: TLND-2297 SQL Code: Update_day_month_table: die Commits innerhalb eines Updates entfernen
--14.10.2022 Thomas Rochlitz, Anton Goldin: TLND-1938: Tablename TEST_ -> OSS_ and some minor updates
--10.10.2022 Christian Kulitza: TLND-1938 update function instead of view now possible
--10.10.2022 Christian Kulitza: TLND-1938 updated procedure Update_MyCom_Counter to use lookup for STO_ID exclusion
--20.09.2022 Thomas Rochlitz: in Update_MyCom_Counter there is a bug for 5G table
--12.07.2022 Thomas Rochlitz: new procedure 
--04.07.2022 Thomas Rochlitz: Update GET_POWER_ASSUMPTION_TEF, Defauld for Router: 450W pauschal
--27.06.2022 Thomas Rochlitz: Insert_ER_SM_DAILY_SO_DATA: Disable meter_valid := -1 for all serialnumbers TLND-2085
--20.06.2022 Thomas Rochlitz: Update_day_month_table, new attribute MAX_CNT_ROUTER added
--20.06.2022 Thomas Rochlitz: Update_MyCom_Counter rownum <= 10000000 -> rownum <= 1000000 -> rownum <= 5000000
--12.06.2022 Thomas Rochlitz: Insert_ER_SM_DAILY_SO_DATA E.STATUS = 'INSTALLED' -> NMS UNAVAILABLE '341990178'
--09.06.2022 Thomas Rochlitz: in der neuen MyCom Quelle sind unschne NE_ID enthalten,diese werden ausgeschlossen mit
--                            where  regexp_like (M5.NE_ID, '^(0|1|2|3|4|5|6|7|8|9)' )
--                            erste Schleife fr 500.000 Zeilen eingebaut
--07.06.2022 Thomas Rochlitz: Weekly_Job: reduced to: (sysdate - 80, sysdate - 10);
--02.06.2022 Thomas Rochlitz: Weekly_Job: reduced to: (sysdate - 40, sysdate - 10);--02.06.2022 Thomas Rochlitz: Weekly_Job: reduced to: (sysdate - 40, sysdate - 10);
--30.05.2022 Thomas Rochlitz: Weekly_Job: tmp wegen Zeit deaktiviert: ER_REPORTING.OSS_ER_FUNCTION.UPDATE_NE_TYP
--14.04.2022 Thomas Rochlitz: den String dvon - dbis auch in das Ende einer Prozedure aufgenommen
--05.04.2022 Thomas Rochlitz: TLND-1920 Tabelle ER_TBL_STO_DAY_AVG entfernt
--30.03.2022 Thomas Rochlitz: TLND-1936 Job fr Langlufer bereitstellen
--28.03.2022 Thomas Rochlitz: TLND-1872 Update Stromlieferant und Weitergabe an Dritte
--                            Reihenfolge der Prozeduren, um mehr im Body anzuzeigen
--22.03.2022 Thomas Rochlitz: TLND-1613 Beide Tabellen nun mit den gleichen Attributen
--21.03.2022 Thomas Rochlitz: Update Update_day_month_table, YYYY -> YY
--15.03.2022 Thomas Rochlitz: TLND-1615 Entfernen von NE_TYP_RADIO NE_TYP_TRANSPORT NE_TYP_CORE
--15.03.2022 Thomas Rochlitz: TLND-1907 Update Budget calculation: seperate WBST by vendor
--09.03.2022 Thomas Rochlitz: TLND-1613 Aufnahme TT_STD und FB%_CELL
--04.03.2022 Thomas Rochlitz; TLND-1871 Update mit TT_STD Wetter Daten
--01.03.2022 Thomas Rochlitz; Update_NE_TYP Erweiterung fr CNT_...
--                            ER_SM_DAILY_SO_DATA_MONTHLY: + CNT_SMART_METER
--21.02.2022 Thomas Rochlitz: Update Make_XHIS, Schleife selber bauen
--19.02.2022 Thomas Rochlitz: Update_NE_TYP Radio Ausgabe mit Count verbessert
--18.02.2022 Thomas Rochlitz: 300 Tage zurck gerechnet
--17.02.2022 Thomas Rochlitz: TLND-1613: Erstellen Prozedure Update_day_month_table, add monthly
--17.02.2022 Thomas Rochlitz: TLND-1805: Erstellen Funktion GET_POWER_ASSUMPTION_TEF
--16.02.2022 Thomas Rochlitz: TLND-1805: Update_Rest: set WBST_VADLID
--16.02.2022 Thomas Rochlitz: Update_rest: Einschrnkung auf Budget Rechnung mit Werten fr CNT_30,31,32
--15.02.2022 Thomas Rochlitz: TLND-1822:update_rest separation: short term, long term, bessere Formulierung der Bedinung mit between
--10.02.2022 Thomas Rochlitz: TLND-1822:update_rest separation: short term, long term
--09.02.2022 Thomas Rochlitz: TLND-1822 MW Start Zeiteinschrnkung auf 5 Tage
--09.02.2022 Thomas Rochlitz: TLND-1822 Update_MyCom_Counter Zeiteinschrnkung auf 5 Tage
--08.02.2022 Thomas Rochlitz: TLND-1805 Update_rest: including budget calculation
--07.02.2022 Thomas Rochlitz: Update_NE_TYP Zeiteinschrnkung auf 5 Tage
--07.02.2022 Thomas Rochlitz: POWER_ASSUMPTION_TEF in die Tageszusammenfassung aufgenommen
--05.02.2022 Thomas Rochlitz: Update_Traffic_FB Zeiteinschrnkung 5 Tage
--04.02.2002 Thomas Rochlitz: EQT_V_IF_74_OI_2: Case in MyCom um ELSE NULL erweitert, und Radio auf NE Typ NotNull gesetzt
--03.02.2022 Thomas Rochlitz: Update_Traffic_2345G: TLND-1806 Umstellung auf EQT_V_IF_74_OI_2, Zeiteinschrnkung 5 Tage
--01.02.2022 Thomas Rochlitz: change commit limit in Update_NE_TYP Radio to 100000, Update Update_MyCom_Counter Where Statments
--30.01.2022 Thomas Rochlitz, entfernen von Order By, setzen von MyCom Power > 0 statt 1
--28.01.2022 Thomas Rochlitz: TLND-1781 update sum calculation
--26.01.2022 Thomas Rochlitz: TLND-1744 STO mit MyCom Radio Ausreiern behandeln
--21.01.2022 Thomas Rochlitz: Update NE_TYPEN, set cursor for radio and REST
--29.09.2021 Thomas Rochlitz: Package created

--create type sto_ids is table of varchar2(9)

   PROCEDURE UPDATE_ER_SM_DAILY_GENERAL;
   PROCEDURE Update_ER_SM_DAILY_SO_DATA2;
   PROCEDURE Insert_ER_SM_DAILY_SO_DATA2;
   FUNCTION get_sto_ids(von_date number, bis_date number) 
    return sto_ids;
   FUNCTION get_sto_ids_date(von_date date, bis_date date) 
    return sto_ids;    
   PROCEDURE Update_OSS_DATA_QUALITY_TALEND;
   PROCEDURE Update_day_month_table;
   PROCEDURE WEEKLY_JOB;
   FUNCTION GET_POWER_ASSUMPTION_TEF (lAIRCONDITION varchar2, lFAN varchar2, lWBST varchar2, c30 number, c31 number, c32 number, cMW number, cRad_Vendor varchar2, cRouter number )
     RETURN NUMBER;
   FUNCTION GET_WBST_VALID (c30 number, c31 number, c32 number, c33 number, lwbst varchar2) 
     RETURN number;
   PROCEDURE Update_ER_SM_ONLY_ONE_VALID_METER_PER_SITE;
   PROCEDURE Update_rest (dvon date, dbis date);
   PROCEDURE Update_Tmp;
   PROCEDURE DAILY_JOB;
   PROCEDURE Update_MyCom_Counter (dvon date, dbis date);
   PROCEDURE Insert_ER_SM_DAILY_SO_DATA;
   PROCEDURE Update_ER_SM_DAILY_SO_DATA;
   PROCEDURE Update_Traffic_FB (dvon date, dbis date);
   PROCEDURE Update_Traffic_2345G (dvon date, dbis date);
   PROCEDURE MAKE_HIS;
   FUNCTION Procedure_stop (t_procedure varchar2) 
     RETURN BOOLEAN;   
   PROCEDURE Update_NE_TYP (dvon date, dbis date);
   PROCEDURE Update_MW (dvon date, dbis date);
END OSS_ER_FUNCTION;

/
