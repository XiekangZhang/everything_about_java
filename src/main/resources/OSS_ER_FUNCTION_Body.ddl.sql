  CREATE OR REPLACE EDITIONABLE PACKAGE BODY "ER_REPORTING"."OSS_ER_FUNCTION" AS

    PROCEDURE UPDATE_ER_SM_DAILY_GENERAL IS
    --contain the logic for new rows in target table
    --former known as Insert_ER_SM_DAILY_SO_DATA
    v_max_id    number := 0;
    v_count number     := 0;
    
    BEGIN
        insert into OSS_LOG values (0, 'Performance', 'UPDATE_ER_SM_DAILY_GENERAL: Start',sysdate, null);
        commit;

        --get max ID
        select a.max_id 
        into   v_max_id
        from   OSS_TABLE_MAX_ID a
        where  a.table_Name = 'ER_SM_DAILY_SO_DATA';
        
        insert into OSS_LOG values (0, 'Info', 'UPDATE_ER_SM_DAILY_GENERAL: get OSS_TABLE_MAX_ID.MAX_ID ' || v_max_id,sysdate, null);
        commit;
        
        --Klima
        update ER_REPORTING.ER_SM_DAILY_SO_DATA a
        set    A.AIRCONDITION  = nvl((select K.KLIMA 
                                      from   ER_REPORTING.NETS_WARTUNG_KLIMA_LUEFTUNG k 
                                      where K.SO_NUMMER = A.STO_ID and K.KLIMA is not null ),'nein')
        where  A.ID > v_max_id
        ;
        v_count := sql%Rowcount;
        insert into OSS_LOG values (0, 'Debug', 'UPDATE_ER_SM_DAILY_GENERAL: Klima: ' || v_count,sysdate, null);
        commit;

        --FAN
        update ER_REPORTING.ER_SM_DAILY_SO_DATA a
        set    A.FAN  = nvl((select K.LUEFTUNG 
                             from   ER_REPORTING.NETS_WARTUNG_KLIMA_LUEFTUNG k 
                             where K.SO_NUMMER = A.STO_ID and K.LUEFTUNG is not null ),'nein')
        --select A.STO_ID, A.FAN
        --from   ER_REPORTING.ER_SM_DAILY_SO_DATA a
        where  A.ID > v_max_id
        --Test SO
        --and    A.STO_ID  in ('345990242', '124992152')
        ;
        v_count := sql%Rowcount;
        insert into OSS_LOG values (0, 'Debug', 'UPDATE_ER_SM_DAILY_GENERAL: FAN: ' || v_count,sysdate, null);
        commit;
        
    
        --EQT
        --tmp Table delete
        delete
        from   er_reporting.oss_tmp_EQT_RADIO a ;
        
        --tmp table insert
        insert into er_reporting.oss_tmp_EQT_RADIO
        select 0 ID
        ,      EQT.site_id
        ,      EQT.VENDOR Radio_vendor
        ,      listagg(product_code, ',' on overflow truncate )   within group (order by TYPE, PRODUCT_CODE) PRODUCT_CODE
        ,      'BTS_CABINET' TYPE
        ,      sysdate INSERT_DATE
        from   (
                select e.SITE_ID, e.VENDOR, e.PRODUCT_CODE, E.TYPE
                from   ER_REPORTING.EQT_ENERGY_REPORTING e
                where  E.STATUS in ('INSTALLED','NMS UNAVAILABLE')
                and    E.TYPE   = 'BTS_CABINET'
                 --nur tmp
                 --and e.site_id = '342992608'
                 --tmp table remove double rows
                 and   e.site_id not in (select d.SITE_ID
                                         from   ER_REPORTING.EQT_ENERGY_REPORTING d
                                            where  d.STATUS in ('INSTALLED','NMS UNAVAILABLE')
                                            and    d.TYPE   = 'BTS_CABINET'
                                            --and    d.site_id = '342992608'
                                         group by d.SITE_ID
                                         having count(distinct d.vendor) > 1
                                        )
                --and    to_date(E.INSERT_DATE, 'DD.MM.YYYY') = to_date((select distinct max( INSERT_DATE) from ER_REPORTING.EQT_ENERGY_REPORTING), 'DD.MM.YYYY')
               ) eqt
        group by EQT.SITE_ID, EQT.VENDOR
        union
        select 0 ID
        ,      EQT_c.SITE_ID
        ,      EQT_c.VENDOR Radio_vendor
        ,      listagg(product_code, ',' on overflow truncate )   within group (order by TYPE, PRODUCT_CODE) PRODUCT_CODE 
        ,      'BTS_CARD' TYPE
        ,      sysdate INSERT_DATE
        from   (
              select e.SITE_ID, e.VENDOR, e.PRODUCT_CODE, E.TYPE, E.INSERT_DATE
              from   ER_REPORTING.EQT_ENERGY_REPORTING e
              where  E.STATUS in ('INSTALLED','NMS UNAVAILABLE')
              and    E.TYPE   !='BTS_CABINET'
              --nur tmp
              --and    E.SITE_ID = '332990117'
              --and    to_date(E.INSERT_DATE, 'DD.MM.YYYY') = to_date((select distinct max( INSERT_DATE) from ER_REPORTING.EQT_ENERGY_REPORTING), 'DD.MM.YYYY')
                 and   e.site_id not in (select d.SITE_ID --, count(distinct d.vendor)
                                         from   ER_REPORTING.EQT_ENERGY_REPORTING d
                                            where  d.STATUS in ('INSTALLED','NMS UNAVAILABLE')
                                            and    d.TYPE   != 'BTS_CABINET'
                                            --and    d.site_id = '332990117'
                                         group by d.SITE_ID
                                         having count(distinct d.vendor) > 1
                                        )
              ) eqt_c
        group by EQT_c.SITE_ID, EQT_c.VENDOR   
        ;        
        
        update ER_REPORTING.ER_SM_DAILY_SO_DATA a
        set    A.RADIO_VENDOR     = (Select o.Radio_vendor
                                     from   er_reporting.oss_tmp_EQT_RADIO o
                                     where  o.site_id = A.STO_ID
                                     and    o.type    = 'BTS_CABINET'
                                     ) 
        ,      A.RADIO_BTS_CABINET = (Select o.PRODUCT_CODE
                                     from   er_reporting.oss_tmp_EQT_RADIO o
                                     where  o.site_id = A.STO_ID
                                     and    o.type    = 'BTS_CABINET'
                                     )
        ,      A.RADIO_BTS_CARD    = (Select o.PRODUCT_CODE
                                     from   er_reporting.oss_tmp_EQT_RADIO o
                                     where  o.site_id = A.STO_ID
                                     and    o.type    = 'BTS_CARD'
                                     )
        where  A.ID > v_max_id
        --Test SO
        --and    A.STO_ID  in ('345990242', '124992152')
        ;
        v_count := sql%Rowcount;
        insert into OSS_LOG values (0, 'Debug', 'UPDATE_ER_SM_DAILY_GENERAL: EQT Radio: ' || v_count,sysdate, null);
        commit;
       

        --site_info
        update ER_REPORTING.ER_SM_DAILY_SO_DATA a
        set    A.WBST = (select SO.WBST from ER_REPORTING.NETS_SITE_INFORMATION so where SO.STANDORT_NR = A.STO_ID and SO.WBST is not null )
        where  A.ID > v_max_id
        ;
        v_count := sql%Rowcount;
        insert into OSS_LOG values (0, 'Debug', 'UPDATE_ER_SM_DAILY_GENERAL: WBST: ' || v_count,sysdate, null);
        commit;
        
        
        --Energy_sale
        --nvl(ENERGY_SALE.FK_ENERGY_SALE_DIRECTION,0) FK_ENERGY_SALE_DIRECTION
        update ER_REPORTING.ER_SM_DAILY_SO_DATA a
        set    A.FK_OSS_ENERGY_SALE_DIRECTION =  (Select min(FK_ENERGY_SALE_DIRECTION) 
                                                  from er_reporting.oss_tmp_fk_energy_sale_direction e 
                                                  where e.zn_normiert = A.SERIALNUMBER 
                                                  group by e.zn_normiert) 
        where  A.ID > v_max_id
        ;
        v_count := sql%Rowcount;
        insert into OSS_LOG values (0, 'Debug', 'UPDATE_ER_SM_DAILY_GENERAL: FK_OSS_ENERGY_SALE_DIRECTION: ' || v_count,sysdate, null);
        commit;

        --Core
        update ER_REPORTING.ER_SM_DAILY_SO_DATA a
        set    A.BEREICH = nvl((select core.STO_ID 
                               from    ER_REPORTING.OSS_SO_CATEGORY core 
                               where   A.STO_ID = CORE.STO_ID
                               and     Core.FK_OSS_REF_CATEGORY = (select CAT.ID
                                                                   from   ER_REPORTING.OSS_REF_CATEGORY cat 
                                                                   where  cat.CATEGORY = 'Core SO' ))
                               ,case a.BEREICH
                                when 'Mobile grün'      then 'Mobile'
                                when 'Mobile'           then 'Mobile'
                                when 'Mobile mit Fixed' then 'Mobile'
                                when 'Fixed'            then 'Fixed'
                                when 'Fixed mit Mobile' then 'Fixed'
                                when 'Fixed grün'       then 'Fixed'
                                else NULL --'xxMobile'
                               end) 
        where  A.ID > v_max_id
        ;
        v_count := sql%Rowcount;
        insert into OSS_LOG values (0, 'Debug', 'UPDATE_ER_SM_DAILY_GENERAL: Core: ' || v_count,sysdate, null);
        commit;
        
        insert into OSS_LOG values (0, 'Info', 'UPDATE_ER_SM_DAILY_GENERAL: Ende',sysdate, null);
        Commit;
        
 
    END UPDATE_ER_SM_DAILY_GENERAL;
    

   PROCEDURE Update_ER_SM_DAILY_SO_DATA2 IS
   --checkt auf Änderungen des gewünschten Tages
   v_count     number := 0;
   d_stop_date date;
   
   BEGIN
     
     insert into OSS_LOG values (0, 'Performance', 'Update_ER_SM_DAILY_SO_DATA2: Start',sysdate, null);
     commit;
     
     IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_ER_SM_DAILY_SO_DATA2') 
       THEN
         insert into OSS_LOG values (0, 'Info', 'Update_ER_SM_DAILY_SO_DATA2: exit wegen Zeit' ,sysdate,null);
         RETURN;
     END IF;

     
     --select *
     --from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY tux_sm
     --where  TUX_SM. --lastupdate ist noch nciht da
     --Insert der Änderungen
     --Update der Änderungen
     update ER_REPORTING.ER_SM_DAILY_SO_DATA t1
     set    (t1.QUELLE, T1.LASTUPDATE, T1.X_KOORD, T1.Y_KOORD, T1.TYP, T1.SERIALNUMBER, T1.FULLSERIALNUMBER, T1.PHASE1POWER, T1.PHASE2POWER, T1.PHASE3POWER
            ,T1.POWER, T1.ENERGYOUT, T1.ENERGY1_HT, T1.ENERGY2_NT, T1.ENERGY, T1.PHASE1VOLTAGE, T1.PHASE2VOLTAGE, T1.PHASE3VOLTAGE
            ,T1.VERBRAUCH_HT_KWH, T1.VERBRAUCH_NT_KWH, T1.INTERVAL_H, T1.BEREICH, T1.EINBAU_DATUM, T1.ABBAU_DATUM, T1.IST_AKTUELLER_STROMZAEHLER
            ,T1.VERBRAUCH_HT_NT
            ,T1.INSERT_DATE 
            ) = 
            (select t2.QUELLE, T2.LASTUPDATE, T2.X_KOORD, T2.Y_KOORD, T2.TYP, T2.SERIALNUMBER, T2.FULLSERIALNUMBER, T2.PHASE1POWER, T2.PHASE2POWER, T2.PHASE3POWER
            ,T2.POWER, T2.ENERGYOUT, T2.ENERGY1_HT, T2.ENERGY2_NT, T2.ENERGY, T2.PHASE1VOLTAGE, T2.PHASE2VOLTAGE, T2.PHASE3VOLTAGE
            ,T2.VERBRAUCH_HT_KWH, T2.VERBRAUCH_NT_KWH, T2.INTERVAL_H, T2.BEREICH, T2.EINBAU_DATUM, T2.ABBAU_DATUM, T2.IST_AKTUELLER_STROMZAEHLER
            ,nvl(T2.VERBRAUCH_HT_KWH,0) + nvl(T2.VERBRAUCH_NT_KWH, 0)
            ,T2.INSERT_DATE
                                              from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY t2
                                              where  t1.ZAEHLER_ID = t2.ZAEHLER_ID
                                              and    t1.AS_START   = t2.AS_START
                                              and    t1.STO_ID     = t2.STO_ID
                                             ) 
     where  (t1.ZAEHLER_ID, t1.AS_START, t1.STO_ID) in 
                    (select TUX.ZAEHLER_ID, TUX.AS_START, TUX.STO_ID
                     from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY tux
                     ,      ER_REPORTING.ER_SM_DAILY_SO_DATA er_target
                     where  TUX.ZAEHLER_ID  = ER_TARGET.ZAEHLER_ID
                     and    TUX.AS_START    = ER_TARGET.AS_START
                     and    TUX.STO_ID      = ER_TARGET.STO_ID
                     and    TUX.STO_ID is not null
                     --and    TUX.LASTUPDATE <> ER_TARGET.LASTUPDATE
                     --and    to_date(TUX.AS_START, 'DD.MM.YY') = to_date('14.08.2021','DD.MM.YY')
                     --and    TUX.STO_ID     = '579990969'
                     --and    ((TUX.VERBRAUCH_HT_KWH <> ER_TARGET.VERBRAUCH_HT_KWH ) OR 
                     --        (TUX.VERBRAUCH_NT_KWH <> ER_TARGET.VERBRAUCH_NT_KWH ) OR
                     --        --TLND-1781 Check sum
                     --        (nvl(ER_TARGET.VERBRAUCH_HT_KWH,0) + nvl(ER_TARGET.VERBRAUCH_NT_KWH,0) != nvl(ER_TARGET.VERBRAUCH_HT_NT,0)))
                     --usage of last_update TUX.LastUpdate > ER.LastUpdate
                     and     TUX.LASTUPDATE > ER_TARGET.LASTUPDATE
                     --26.10.2022 Thomas Rochlitz: TLND-2315 ER many Temp-Segment problems, recheck the source code
                     --1000000 -> 500000
                     and    rownum <= 500000
                     --order by TUX.AS_START
                    ) 
     ;


     --##Ändert sich der STO?
     --set NULL for traffic, ...
      
     --Log Ausgabe
     v_count:= sql%Rowcount;
     insert into OSS_LOG values (0, 'Info', 'Update_ER_SM_DAILY_SO_DATA2: count of updates: ' || v_count ,sysdate, null);

     
     insert into OSS_LOG values (0, 'Info', 'Update_ER_SM_DAILY_SO_DATA2: Ende',sysdate, null);
     Commit;

   END Update_ER_SM_DAILY_SO_DATA2;



    PROCEDURE Insert_ER_SM_DAILY_SO_DATA2 IS
    --26.10.2022 Insert daily ER_REPORTING.ER_SM_DAILY_SO_DATA2
    --simple insert, logic transfered to UPDATE_ER_SM_DAILY_GENERAL
    --
    v_c     number     := 0;
    v_count number     := 0;
    v_max_id number    := 0;
    v_max_date date        ;
    v_max_date_new date    ;

    BEGIN
        --Log
        insert into OSS_LOG values (0, 'Performance', 'Insert_ER_SM_DAILY_SO_DATA2: Start',sysdate, null);
        commit;
        
        --save  max(ER_SM_DAILY_SO_DATA.ID) for Update_ER_SM_DAILY_SO_DATA2
        select max(A.ID) MAX_ID
        into   v_max_id
        from   ER_REPORTING.ER_SM_DAILY_SO_DATA a;

        update OSS_TABLE_MAX_ID 
        set    MAX_ID      = v_max_id
        ,      INSERT_DATE = sysdate
        where  TABLE_NAME  = 'ER_SM_DAILY_SO_DATA';

        insert into OSS_LOG values (0, 'Info', 'Insert_ER_SM_DAILY_SO_DATA2: get ER_SM_DAILY_SO_DATA,max(A.ID) set OSS_TABLE_MAX_ID.v_max_id ' || v_max_id,sysdate, null);
        commit;


        --get Last INSERT_DATE
        select md.max_date 
        into   v_max_date
        from   er_reporting.OSS_TABLE_MAX_ID md
        where  TABLE_NAME  = 'TUX_SMART_METER_POWER_HOURLY';
        insert into OSS_LOG values (0, 'Debug', 'Insert_ER_SM_DAILY_SO_DATA2: get OSS_TABLE_MAX_ID,MAX_DATE ' || v_max_date,sysdate, null);
        commit;
        

        --set Last INSERT_DATE
        select max(A.INSERT_DATE ) MAX_DATE
        into   v_max_date_new
        from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY a;

        update OSS_TABLE_MAX_ID 
        set    MAX_DATE    = v_max_date_new
        ,      INSERT_DATE = sysdate
        where  TABLE_NAME  = 'TUX_SMART_METER_POWER_HOURLY';

        insert into OSS_LOG values (0, 'Info', 'Insert_ER_SM_DAILY_SO_DATA2: get TUX_SMART_METER_POWER_HOURLY.max(A.INSERT_DATE ) set OSS_TABLE_MAX_ID.MAX_DATE ' || v_max_date_new,sysdate, null);
        commit;

                
        LOOP
          IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Insert_ER_SM_DAILY_SO_DATA2') 
            THEN
              insert into OSS_LOG values (0, 'Info', 'Insert_ER_SM_DAILY_SO_DATA2: exit wegen Zeit' ,sysdate,null);
              exit;
          END IF;
                       
            --Insert data
            --26.10.2022 8:30 max(ER_SM_DAILY_SO_DATA.ID) 189892266 
            --
            --create table er_reporting.ER_SM_DAILY_SO_DATA as
            insert into ER_REPORTING.ER_SM_DAILY_SO_DATA 
             (STO_ID, AS_START, ZAEHLER_ID, X_KOORD, Y_KOORD, TYP, SERIALNUMBER, FULLSERIALNUMBER, PHASE1POWER, PHASE2POWER, PHASE3POWER       
              ,POWER, ENERGYOUT, ENERGY1_HT, ENERGY2_NT, ENERGY, PHASE1VOLTAGE, PHASE2VOLTAGE, PHASE3VOLTAGE, VERBRAUCH_HT_KWH, VERBRAUCH_NT_KWH
              ,INTERVAL_H, Bereich, EINBAU_DATUM, ABBAU_DATUM, IST_AKTUELLER_STROMZAEHLER, QUELLE, LASTUPDATE
              ,Verbrauch_HT_NT
              ,meter_valid  
              --SO
              --,aircondition, fan
              --WBST  
              ,WBST
              --PowerResale
              ,FK_OSS_ENERGY_SALE_DIRECTION
              --Radio Equipment
              ,Radio_vendor, Radio_BTS_CABINET, Radio_BTS_CARD
             )
            select TUX_POWER_H.STO_ID       
            ,      TUX_POWER_H.AS_START       
            ,      TUX_POWER_H.ZAEHLER_ID       
            ,      TUX_POWER_H.X_KOORD       
            ,      TUX_POWER_H.Y_KOORD       
            ,      TUX_POWER_H.TYP       
            ,      TUX_POWER_H.SERIALNUMBER       
            ,      TUX_POWER_H.FULLSERIALNUMBER       
            ,      TUX_POWER_H.PHASE1POWER       
            ,      TUX_POWER_H.PHASE2POWER       
            ,      TUX_POWER_H.PHASE3POWER       
            ,      TUX_POWER_H.POWER       
            ,      TUX_POWER_H.ENERGYOUT       
            ,      TUX_POWER_H.ENERGY1_HT       
            ,      TUX_POWER_H.ENERGY2_NT       
            ,      TUX_POWER_H.ENERGY       
            ,      TUX_POWER_H.PHASE1VOLTAGE       
            ,      TUX_POWER_H.PHASE2VOLTAGE       
            ,      TUX_POWER_H.PHASE3VOLTAGE       
            ,      nvl(TUX_POWER_H.VERBRAUCH_HT_KWH, 0)   VERBRAUCH_HT_KWH    
            ,      nvl(TUX_POWER_H.VERBRAUCH_NT_KWH, 0)   VERBRAUCH_NT_KWH  
            ,      TUX_POWER_H.INTERVAL_H       
            --,      TUX_POWER_H.BEREICH
            /*,      nvl(core.corebereich,
                       case TUX_POWER_H.BEREICH
                        when 'Mobile grün'      then 'Mobile'
                        when 'Mobile'           then 'Mobile'
                        when 'Mobile mit Fixed' then 'Mobile'
                        when 'Fixed'            then 'Fixed'
                        when 'Fixed mit Mobile' then 'Fixed'
                        when 'Fixed grün'       then 'Fixed'
                        else NULL --'xxMobile'
                       end) */ 
            ,      TUX_POWER_H.BEREICH  Bereich     
            ,      TUX_POWER_H.EINBAU_DATUM       
            ,      TUX_POWER_H.ABBAU_DATUM       
            ,      TUX_POWER_H.IST_AKTUELLER_STROMZAEHLER
            ,      TUX_POWER_H.QUELLE       
            ,      TUX_POWER_H.LASTUPDATE
            --Verbrauch_HT_NT
            ,      nvl(TUX_POWER_H.VERBRAUCH_HT_KWH,0) + nvl(TUX_POWER_H.VERBRAUCH_NT_KWH,0) Verbrauch_HT_NT
            --meter_valid: -1, 0
            ,      -1
            --SO
            --,      'x' --nvl(KLIMA.KLIMA, 'nein') aircondition
            --,      'x' --nvl(KLIMA.LUEFTUNG, 'nein') fan
            --WBST  
            ,      NULL --SITE_INFO.WBST
            --PowerResale
            ,      NULL --nvl(ENERGY_SALE.FK_ENERGY_SALE_DIRECTION,0) FK_ENERGY_SALE_DIRECTION 
            --Radio Equipment
            ,      NULL --eqt.Radio_vendor
            ,      NULL --eqt.Radio_BTS_CABINET
            ,      NULL --eqt_card.Radio_BTS_CARD
            --Transport Equipment
            --the tables itself
            from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY tux_power_h
            where  TUX_POWER_H.STO_ID is not null
            --and    TUX_POWER_H.STO_ID in (select site_id from ER_REPORTING.OSS_EXAMPLES )
            --and  (TUX_POWER_H.ZAEHLER_ID, TUX_POWER_H.AS_START ) not in (select EH.ZAEHLER_ID, EH.AS_START
            --                                                             from   ER_REPORTING.ER_SM_DAILY_SO_DATA eh )
            and    TUX_POWER_H.INSERT_DATE > v_max_date
            --26.10.2022 Thomas Rochlitz: TLND-2315 ER many Temp-Segment problems, recheck the source code, split code
            --500000 -> 50000 -> 25000
            --500000 -> 100000 -> 10000 -> 500000
            and    rownum <= 500000
            ;

            v_c         := sql%Rowcount;
            v_count     := v_count + v_c ;

            insert into OSS_LOG values (0, 'Debug', 'Insert_ER_SM_DAILY_SO_DATA2: ' || v_c || ' ' || v_count,sysdate,null);
            commit;
           
            EXIT WHEN v_c < 1;

        END LOOP ;


        insert into OSS_LOG values (0, 'Info', 'Insert_ER_SM_DAILY_SO_DATA2: Ende  count: ' || v_count,sysdate,null);
        commit;

    END Insert_ER_SM_DAILY_SO_DATA2;
   

    --TLND-1938 function get_sto_ids
    FUNCTION get_sto_ids (
        von_date NUMBER,
        bis_date NUMBER
    ) RETURN sto_ids IS
        l_sto_ids   sto_ids := sto_ids ();
        l_idx       INTEGER := 0;
    BEGIN
        FOR r IN (
            SELECT
                sto_id
            FROM
                oss_sto_id_exclude
            WHERE
                start_exclude <= ( SYSDATE - von_date )
                AND   ( SYSDATE - bis_date ) <= end_exclude
        ) LOOP
            l_sto_ids.extend;
            l_idx := l_idx + 1;
            l_sto_ids(l_idx) := r.sto_id;
        END LOOP;
    
        RETURN l_sto_ids;
    END; 
    
    FUNCTION get_sto_ids_date (
        von_date date,
        bis_date date
    ) RETURN sto_ids IS
        l_sto_ids   sto_ids := sto_ids ();
        l_idx       INTEGER := 0;
    BEGIN
        FOR r IN (
            SELECT
                sto_id
            FROM
                oss_sto_id_exclude
            WHERE
                start_exclude <= von_date
                AND   bis_date <= end_exclude
        ) LOOP
            l_sto_ids.extend;
            l_idx := l_idx + 1;
            l_sto_ids(l_idx) := r.sto_id;
        END LOOP;
    
        RETURN l_sto_ids;
    END;    

   PROCEDURE Update_OSS_DATA_QUALITY_TALEND IS
     --Update table for Tableau reporting

   BEGIN  
    --Talend Data Quality
    delete er_reporting.OSS_DATA_QUALITY_TALEND;
    insert into er_reporting.OSS_DATA_QUALITY_TALEND
    select 0 ID, 'TUX_2G_CELL_VOL' TABLE_NAME,TRAFFIC2G.TAG DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.TUX_2G_CELL_VOL Traffic2g
    group by TRAFFIC2G.TAG
    union
    select 0 ID, 'TUX_3G_CELL_VOL' TABLE_NAME,TRAFFIC3G.TAG DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.TUX_3G_CELL_VOL Traffic3g
    group by TRAFFIC3G.TAG
    union
    select 0 ID, 'TUX_4G_CELL_VOL' TABLE_NAME,TRAFFIC4G.TAG DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.TUX_4G_CELL_VOL Traffic4g
    group by TRAFFIC4G.TAG
    union
    select 0 ID, 'TUX_5G_CELL_VOL' TABLE_NAME,TRAFFIC5G.TAG DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.TUX_5G_CELL_VOL Traffic5g
    group by TRAFFIC5G.TAG
    union
    select 0 ID, 'MYC_COUNTER_2G_POWER_HOURLY' TABLE_NAME,trunc(MY2G.AS_START) DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.MYC_COUNTER_2G_POWER_HOURLY my2g
    group by trunc(MY2G.AS_START)
    union
    select 0 ID, 'MYC_COUNTER_3G_POWER_HOURLY' TABLE_NAME,trunc(MY3G.AS_START) DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.MYC_COUNTER_3G_POWER_HOURLY my3g
    group by trunc(MY3G.AS_START)
    union
    select 0 ID, 'MYC_COUNTER_4G_POWER_HOURLY' TABLE_NAME,trunc(MY4G.AS_START) DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.MYC_COUNTER_4G_POWER_HOURLY my4g
    group by trunc(MY4G.AS_START)
    union
    select 0 ID, 'MYC_COUNTER_5G_POWER_HOURLY' TABLE_NAME,trunc(MY5G.AS_START) DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.MYC_COUNTER_5G_POWER_HOURLY my5g
    group by trunc(MY5G.AS_START)
    union
    select 0 ID, 'TUX_SMART_METER_POWER_HOURLY' TABLE_NAME,trunc(a.AS_START) DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY a
    group by trunc(a.AS_START)
    union
    select 0 ID, 'ER_SM_DAILY_SO_DATA' TABLE_NAME,trunc(er_data.AS_START) DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.ER_SM_DAILY_SO_DATA er_data
    group by trunc(er_data.AS_START)
    union
    select 0 ID, 'TUX_DWD_WETTERDATEN' TABLE_NAME,trunc(DWD.MESS_DATUM) DATE_VALUES, count(*) COUNT_VALUES, sysdate INSERT_DATE
    from   ER_REPORTING.TUX_DWD_WETTERDATEN dwd
    group by trunc(DWD.MESS_DATUM )
    ;
     
   END Update_OSS_DATA_QUALITY_TALEND;
   

   PROCEDURE WEEKLY_JOB IS
     --geht über alle Tage - 6
     --TLND-1936 Job für Langläufer bereitstellen

   BEGIN  
     --03 die Traffic Daten nach Technologie übertragen
     --ER_REPORTING.OSS_ER_FUNCTION.Update_Traffic_2345G (to_date('01.08.2021','DD.MM.YYYY'), sysdate - 10);
     ER_REPORTING.OSS_ER_FUNCTION.Update_Traffic_2345G (sysdate - 80, sysdate - 10);
     --04 die Traffic Daten nach Frequenzband übertragen
     --ER_REPORTING.OSS_ER_FUNCTION.Update_Traffic_FB (to_date('01.08.2021','DD.MM.YYYY'), sysdate - 10);
     ER_REPORTING.OSS_ER_FUNCTION.Update_Traffic_FB (sysdate - 80, sysdate - 10);
     --05 die MyCom Power Daten übertragen
     --ER_REPORTING.OSS_ER_FUNCTION.UPDATE_MYCOM_COUNTER (to_date('01.08.2021','DD.MM.YYYY'), sysdate - 10);
     ER_REPORTING.OSS_ER_FUNCTION.UPDATE_MYCOM_COUNTER (sysdate - 80, sysdate - 10);
     --06 NE_TYP
     --ER_REPORTING.OSS_ER_FUNCTION.UPDATE_NE_TYP (to_date('01.08.2021','DD.MM.YYYY'), sysdate - 10);
     ER_REPORTING.OSS_ER_FUNCTION.UPDATE_NE_TYP (sysdate - 80, sysdate - 10);
     --07 MW
     --ER_REPORTING.OSS_ER_FUNCTION.UPDATE_MW (to_date('01.08.2021','DD.MM.YYYY'), sysdate - 10);
     ER_REPORTING.OSS_ER_FUNCTION.UPDATE_MW (sysdate - 80, sysdate - 10);
     
     --Rest
     --ER_REPORTING.OSS_ER_FUNCTION.Update_rest (to_date('01.08.2021','DD.MM.YYYY'), sysdate - 10);
     ER_REPORTING.OSS_ER_FUNCTION.Update_rest (sysdate - 80, sysdate - 10);
     
   END WEEKLY_JOB;
   
   PROCEDURE Update_day_month_table IS
   --create new tables for Day and month
   v_count number     := 0;
   BEGIN

     insert into OSS_LOG values (0, 'Performance', 'Update_day_month_table: Start',sysdate,null);
     commit;

     --ER_SM_DAILY_SO_DATA auf Tagesbasis neu anlegen
     --TABLE er_reporting.ER_TBL_STO_DAY_AVG PURGE ;
     /*insert into OSS_LOG values (0, 'Info', 'Update_day_month_table: ER_TBL_STO_DAY_AVG delete',sysdate,null);
     commit;
     DELETE FROM er_reporting.ER_TBL_STO_DAY_AVG PURGE;
     insert into OSS_LOG values (0, 'Info', 'Update_day_month_table: ER_TBL_STO_DAY_AVG insert',sysdate,null);
     commit;
 
     --create table er_reporting.ER_TBL_STO_DAY_AVG as
     insert into er_reporting.ER_TBL_STO_DAY_AVG
     select A.STO_ID, to_date(A.AS_START,'DD.MM.YY') AS_START
     ,      avg(A.VERBRAUCH_HT_NT)                AVG_POWER_KW
     ,      avg(A.RADIO_VERBRAUCH_KW)             AVG_RAD_POWER_KW
     ,      avg(a.ALL_TRAFFIC_MB)                 AVG_ALL_TRAFFIC_MB
     ,      avg(a.ALL_TRAFFIC_VS_POWER_MB_KW)     AVG_ALL_TRAFFIC_VS_POWER_MB_KW
     ,      avg(A.ALL_TRAFFIC_VS_RAD_POWER_MB_KW) AVG_ALL_TRAFF_VS_RAD_POW_MB_KW
     ,      avg(A.POWER_ASSUMPTION_TEF)           AVG_ALL_POWER_ASSUMPTION_TEF
     from   ER_REPORTING.ER_SM_DAILY_SO_DATA a
     where  A.METER_VALID = -1
     and    A.ALL_TRAFFIC_MB is not null
     --and    A.ALL_TRAFFIC_VS_POWER_MB_KW is not null
     group by A.STO_ID, to_date(A.AS_START,'DD.MM.YY')
     ;
     v_count     := sql%Rowcount;
     insert into OSS_LOG values (0, 'Info', 'Update_day_month_table: Insert ER_TBL_STO_DAY_AVG: ' || v_count,sysdate,null);
     v_count     := 0;
     commit;*/
     
     --#Daily
     insert into OSS_LOG values (0, 'Info', 'Update_day_month_table: ER_SM_DAILY_SO_DATA_DAILY delete',sysdate,null);
     commit;
     DELETE FROM ER_REPORTING.ER_SM_DAILY_SO_DATA_DAILY PURGE;
     insert into OSS_LOG values (0, 'Info', 'Update_day_month_table: ER_SM_DAILY_SO_DATA_DAILY deleted',sysdate,null);
     --18.10.2022 Thomas Rochlitz: TLND-2297 SQL Code: Update_day_month_table: die Commits innerhalb eines Updates entfernen
     --commit;

     --create table ER_REPORTING.ER_SM_DAILY_SO_DATA_DAILY as
     insert into ER_REPORTING.ER_SM_DAILY_SO_DATA_DAILY
     select 0 ID
     ,      A.STO_ID
     ,      to_date(A.AS_START,'DD.MM.YY') AS_START
     ,      min(A.RADIO_VENDOR)                            RADIO_VENDOR
     ,      min(A.WBST)                                    WBST
     ,      max(a.WBST_VALID)                              WBST_VALID
     ,      min(A.AIRCONDITION)                            AIRCONDITION
     ,      min(A.FAN)                                     FAN
     ,      round(avg(A.POWER_ASSUMPTION_TEF),3)           AVG_ALL_POWER_ASSUMPTION_TEF
     ,      round(avg(A.VERBRAUCH_HT_NT),3)                AVG_POWER_KW
     ,      round(median(A.VERBRAUCH_HT_NT),3)             AVG_POWER_KW_Median
     ,      round(stddev(A.VERBRAUCH_HT_NT),3)             AVG_POWER_KW_Standardabw
     ,      round(avg(A.POWER_ASSUMPTION_TEF),3) - round(avg(A.VERBRAUCH_HT_NT),3) Difference_Absolute
            --Division durch 0 verhindern
     ,      case 
              WHEN avg(A.POWER_ASSUMPTION_TEF) > 0 THEN round(avg(A.VERBRAUCH_HT_NT) * 100 / avg(A.POWER_ASSUMPTION_TEF),3)
            end Erfuellungsgrad_Prozent
     ,      round(avg(A.RADIO_VERBRAUCH_KW),3)             AVG_RAD_POWER_KW
     ,      round(avg(a.ALL_TRAFFIC_MB),3)                 AVG_ALL_TRAFFIC_MB
     ,      round(avg(a.ALL_TRAFFIC_VS_POWER_MB_KW),3)     AVG_ALL_TRAFFIC_VS_POWER_MB_KW
     ,      round(avg(A.ALL_TRAFFIC_VS_RAD_POWER_MB_KW),3) AVG_ALL_TRAFF_VS_RAD_POW_MB_KW
     ,      count(distinct A.ZAEHLER_ID)                   CNT_SMART_METER
     ,      round(sum(A.VERBRAUCH_HT_NT),3)                SUM_POWER_KWH
     --neue Attribute
     ,      MIN(A.TT_STD)                                  MIN_TT_STD
     ,      ROUND(AVG(A.TT_STD),3)                         AVG_TT_STD
     ,      MAX(A.TT_STD)                                  MAX_TT_STD
     ,      MAX(A.FB700_CELL)                              MAX_FB700_CELL
     ,      MAX(A.FB800_CELL)                              MAX_FB800_CELL
     ,      MAX(A.FB900_CELL)                              MAX_FB900_CELL
     ,      MAX(A.FB1800_CELL)                             MAX_FB1800_CELL
     ,      MAX(A.FB2100_CELL)                             MAX_FB2100_CELL
     ,      MAX(A.FB2600_CELL)                             MAX_FB2600_CELL
     ,      MAX(A.FB3500_CELL)                             MAX_FB3500_CELL
     ,      MAX(A.CNT_ROUTER)                              MAX_CNT_ROUTER
     from   ER_REPORTING.ER_SM_DAILY_SO_DATA a
     where  A.METER_VALID           = -1
     --and    A.WBST                  in ('WBST1','WBST2','WBST3','WBST4','WBST5','WBST6','WBST7','WBST8','WBST9')
     --nur gültige Budget Vorgaben verwenden. wegem Division / 0
     --and    A.POWER_ASSUMPTION_TEF   is not null
     --alle Monate vor aktuellem Monat
     and    A.AS_START               < sysdate - 1
     --Test Menge
     --and    A.STO_ID    in ( '457990100','128992984','349990002','210990622','149990721')
     --and    A.STO_ID       in (select B.SITE_ID from ER_REPORTING.OSS_EXAMPLES b )
     group by 
     --Group by Day/ Month
     --to_char(A.AS_START, 'YYYYMM')
     A.STO_ID
     ,to_date(A.AS_START,'DD.MM.YY')
     --order by 2,3
     ;
     v_count     := sql%Rowcount;
     insert into OSS_LOG values (0, 'Info', 'Update_day_month_table: Insert ER_SM_DAILY_SO_DATA_DAILY_2: ' || v_count,sysdate,null);
     v_count     := 0;
     commit;

     
     
     
     --Monthly ER_REPORTING.ER_SM_DAILY_SO_DATA_MONTHLY
     insert into OSS_LOG values (0, 'Info', 'Update_day_month_table: ER_SM_DAILY_SO_DATA_MONTHLY delete',sysdate,null);
     commit;
     DELETE FROM ER_REPORTING.ER_SM_DAILY_SO_DATA_MONTHLY PURGE;
     insert into OSS_LOG values (0, 'Info', 'Update_day_month_table: ER_SM_DAILY_SO_DATA_MONTHLY deleted',sysdate,null);
     --18.10.2022 Thomas Rochlitz: TLND-2297 SQL Code: Update_day_month_table: die Commits innerhalb eines Updates entfernen
     --commit;

    --create table ER_REPORTING.ER_SM_DAILY_SO_DATA_MONTHLY as
    insert into ER_REPORTING.ER_SM_DAILY_SO_DATA_MONTHLY
    select 0 ID
    ,      A.STO_ID
    ,      to_date('01.' || to_char(A.AS_START, 'MM.YYYY'),'DD.MM.YYYY') AS_START
    ,      min(A.RADIO_VENDOR)                            RADIO_VENDOR
    ,      min(A.WBST)                                    WBST
    ,      max(a.WBST_VALID)                              WBST_VALID
    ,      min(A.AIRCONDITION)                            AIRCONDITION
    ,      min(A.FAN)                                     FAN
    ,      round(avg(A.POWER_ASSUMPTION_TEF),3)           AVG_ALL_POWER_ASSUMPTION_TEF
    ,      round(avg(A.VERBRAUCH_HT_NT),3)                AVG_POWER_KW
    ,      round(median(A.VERBRAUCH_HT_NT),3)             AVG_POWER_KW_Median
    ,      round(stddev(A.VERBRAUCH_HT_NT),3)             AVG_POWER_KW_Standardabw
    ,      round(avg(A.POWER_ASSUMPTION_TEF),3) - round(avg(A.VERBRAUCH_HT_NT),3) Difference_Absolute
           --Division durch 0 verhindern
    ,      case 
             WHEN avg(A.POWER_ASSUMPTION_TEF) > 0 THEN round(avg(A.VERBRAUCH_HT_NT) * 100 / avg(A.POWER_ASSUMPTION_TEF),3)
           end Erfuellungsgrad_Prozent
    ,      round(avg(A.RADIO_VERBRAUCH_KW),3)             AVG_RAD_POWER_KW
    ,      round(avg(a.ALL_TRAFFIC_MB),3)                 AVG_ALL_TRAFFIC_MB
    ,      round(avg(a.ALL_TRAFFIC_VS_POWER_MB_KW),3)     AVG_ALL_TRAFFIC_VS_POWER_MB_KW
    ,      round(avg(A.ALL_TRAFFIC_VS_RAD_POWER_MB_KW),3) AVG_ALL_TRAFF_VS_RAD_POW_MB_KW
    ,      count(distinct A.ZAEHLER_ID)                   CNT_SMART_METER
    ,      MIN(A.TT_STD)                                  MIN_TT_STD
    ,      ROUND(AVG(A.TT_STD),3)                         AVG_TT_STD
    ,      MAX(A.TT_STD)                                  MAX_TT_STD
    ,      MAX(A.FB700_CELL)                              MAX_FB700_CELL
    ,      MAX(A.FB800_CELL)                              MAX_FB800_CELL
    ,      MAX(A.FB900_CELL)                              MAX_FB900_CELL
    ,      MAX(A.FB1800_CELL)                             MAX_FB1800_CELL
    ,      MAX(A.FB2100_CELL)                             MAX_FB2100_CELL
    ,      MAX(A.FB2600_CELL)                             MAX_FB2600_CELL
    ,      MAX(A.FB3500_CELL)                             MAX_FB3500_CELL
    --neue Attribute
    ,      round(sum(A.VERBRAUCH_HT_NT),3)                SUM_POWER_KWH
    ,      MAX(A.CNT_ROUTER)                              MAX_CNT_ROUTER
    from   ER_REPORTING.ER_SM_DAILY_SO_DATA a
    where  A.METER_VALID           = -1
    --and    A.WBST                  in ('WBST1','WBST2','WBST3','WBST4','WBST5','WBST6','WBST7','WBST8','WBST9')
    --nur gültige Budget Vorgaben verwenden. wegem Division / 0
    --and    A.POWER_ASSUMPTION_TEF   is not null
    --alle Monate vor aktuellem Monat
    and    A.AS_START               < to_date('01.' || to_char(sysdate, 'MM.YYYY'),'DD.MM.YYYY') - 1
    --Test Menge
    --and    A.STO_ID    in ( '457990100','128992984','349990002','210990622','149990721')
    --and    A.STO_ID       in (select B.SITE_ID from ER_REPORTING.OSS_EXAMPLES b )
    group by 
    --Group by Day/ Month
    --to_char(A.AS_START, 'YYYYMM')
    A.STO_ID
    ,to_date('01.' || to_char(A.AS_START, 'MM.YYYY'),'DD.MM.YYYY')
    --order by 2,3
    ;
     v_count     := sql%Rowcount;
     insert into OSS_LOG values (0, 'Info', 'Update_day_month_table: Insert ER_SM_DAILY_SO_DATA_MONTHLY: ' || v_count,sysdate,null);
     v_count     := 0;
     commit;

     insert into OSS_LOG values (0, 'Performance', 'Update_day_month_table: Ende',sysdate,null);
     commit;


   END Update_day_month_table;

   FUNCTION GET_POWER_ASSUMPTION_TEF (lAIRCONDITION varchar2, lFAN varchar2, lWBST varchar2
                                     ,c30 number, c31 number, c32 number
                                     ,cMW number, cRad_Vendor varchar2
                                     ,cRouter number )
     RETURN number IS
   --get GET_POWER_ASSUMPTION_TEF for given parameter
   BEGIN
   
     RETURN  (
            --Infra
              (case lAIRCONDITION
                  when 'ja' then 1000
                  else 0
                end)  
            +  (case lFAN
                  when 'ja' then 30
                  else 0
                end)
            --Transport         
            --Microwave
            +    nvl(cMW,0) * 70
            --Router
            --  
            +    nvl(cRouter,0) * 450
            --Radio     
/*            +  (case lWBST
                  when 'WBST1' then 1930
                  when 'WBST2' then 3060
                  when 'WBST3' then 1890
                  when 'WBST4' then 3410
                  when 'WBST5' then 4180
                  when 'WBST6' then 3890
                  when 'WBST7' then 5220
                  when 'WBST8' then 3120
                  when 'WBST9' then 4450
                  else 0
                 end)*/
            +  (case cRad_Vendor
                --Werte vom 28.02.2022
                  WHEN 'Nokia'  THEN 
                    (case lWBST
                      when 'WBST1' then 2160
                      when 'WBST2' then 3260
                      when 'WBST3' then 2020
                      when 'WBST4' then 3410 --old
                      when 'WBST5' then 4420
                      when 'WBST6' then 4060
                      when 'WBST7' then 5390
                      when 'WBST8' then 3290
                      when 'WBST9' then 4490
                      when 'WBSTS' then 3390
                      else 0
                     end)
                  WHEN 'Huawei' THEN 
                    (case lWBST
                      when 'WBST1' then 1260
                      when 'WBST2' then 2440
                      when 'WBST3' then 1690
                      when 'WBST4' then 3410  --old
                      when 'WBST5' then 3580
                      when 'WBST6' then 3180
                      when 'WBST7' then 4300
                      when 'WBST8' then 2560
                      when 'WBST9' then 3480
                      when 'WBSTS' then 6780
                      else 0
                     end)
                  ELSE 0
                end)
            --2G und 4G Reste, 2G 840W, 3G 640W, 4G 870W
            +    nvl(c30,0) * 840         
            +    nvl(c31,0) * 640         
            +    nvl(c32,0) * 870
            --auf kW umrechnen / 1000       
               ) / 1000;
      
   END GET_POWER_ASSUMPTION_TEF;
     
   FUNCTION GET_WBST_VALID (c30 number, c31 number, c32 number, c33 number, lwbst varchar2) 
     RETURN number IS
   --get feedback, if WBST is only 33 TRUE, or are there more elements, FALSE  
   BEGIN
     IF c30 is null AND c31 is NULL AND c32 is NULL AND c33 IS NOT NULL AND lwbst IS NOT NULL-- (nvl(c30,0) + nvl(c31,0) + nvl(c32,0) + nvl2(c33,0,1) + nvl2(lwbst,0,1)) <> 0
       THEN RETURN -1;
       ELSE RETURN 0;
     END IF ;
   END GET_WBST_VALID;
     
   PROCEDURE Update_ER_SM_ONLY_ONE_VALID_METER_PER_SITE IS
   --Filling table ER_SM_ONLY_ONE_VALID_METER_PER_SITE once per day
   v_count number     := 0;
   BEGIN
     insert into OSS_LOG values (0, 'Performance', 'Update_ER_SM_ONLY_ONE_VALID_METER_PER_SITE: Start',sysdate,null);
     commit;

     --#delete data
     DELETE ER_reporting.ER_SM_ONLY_ONE_VALID_METER_PER_SITE  PURGE;
     insert into OSS_LOG values (0, 'Info', 'Update_ER_SM_ONLY_ONE_VALID_METER_PER_SITE: ER_SM_ONLY_ONE_VALID_METER_PER_SITE deleted',sysdate,null);
     commit;
     
     --Insert data
     insert into er_reporting.ER_SM_ONLY_ONE_VALID_METER_PER_SITE
     select STO_ID, STO_ID STO_ID_TXT
     from   (
             select A.STO_ID
             from   ER_REPORTING.ER_SM_DAILY_SO_DATA a
             group by A.STO_ID
             having count(distinct A.ZAEHLER_ID ) < 2
            )
     where  sto_id in (select B.STO_ID from ER_REPORTING.ER_SM_DAILY_SO_DATA b where B.METER_VALID = -1 );
     v_count     := sql%Rowcount;
     insert into OSS_LOG values (0, 'Info', 'Update_ER_SM_ONLY_ONE_VALID_METER_PER_SITE: Insert: ' || v_count,sysdate,null);
     commit;
     

     insert into OSS_LOG values (0, 'Performance', 'Update_ER_SM_ONLY_ONE_VALID_METER_PER_SITE: Ende',sysdate,null);
     commit;

   END Update_ER_SM_ONLY_ONE_VALID_METER_PER_SITE;

   PROCEDURE Update_rest (dvon date, dbis date) IS
   --Update weiterer TAbellen nach der Zieltabelle
   --04.03.2022 Thomas Rochlitz; TLND-1871 Update mit TT_STD Wetter Daten
   --TLND-1822: separation: short term, long term

   v_count number     := 0;
   v_count_all number := 0;
   v_c     number     := 0;
   
   CURSOR C_DWD_WETTERDATEN IS  
        --Wetterdaten
        select G.STO_ID, W.MESS_DATUM AS_START, W.TT_STD, W.STATIONS_ID 
        ,      G.POLYGON_NAME
        from   ER_REPORTING.TUX_DWD_WETTERDATEN w
               JOIN ER_REPORTING.TUX_STO_ZU_GEBIETE g
                 ON to_number(G.ATTR1) = W.STATIONS_ID
        where  W.MESS_DATUM between dvon and dbis
        --Test STO
        --and    G.STO_ID = '127990324'
        --order by G.STO_ID, W.MESS_DATUM
        ;

   
   BEGIN
     insert into OSS_LOG values (0, 'Performance', 'Update_rest: Start (' || dvon || '-' || dbis || ')',sysdate,null);
     commit;
     
     --08.02.2022 Thomas Rochlitz: TLND-1805 Update_rest: including budget calculation
     insert into OSS_LOG values (0, 'Info', 'Update_rest: including budget calculation',sysdate,null);
     commit;
     
     LOOP
        IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_rest') 
          THEN
            insert into OSS_LOG values (0, 'Info', 'Update_rest: exit wegen Zeit' ,sysdate,null);
            exit;
        END IF;
        update ER_REPORTING.ER_SM_DAILY_SO_DATA a
        set    A.POWER_ASSUMPTION_TEF = ER_REPORTING.OSS_ER_FUNCTION.GET_POWER_ASSUMPTION_TEF (A.AIRCONDITION, A.FAN, A.WBST, A.CNT_30, A.CNT_31, A.CNT_32, A.CNT_MW, A.RADIO_VENDOR, A.CNT_ROUTER )
        where  A.METER_VALID          = -1
        --and    A.POWER_ASSUMPTION_TEF is null
        and    nvl(A.POWER_ASSUMPTION_TEF,0) <> ER_REPORTING.OSS_ER_FUNCTION.GET_POWER_ASSUMPTION_TEF (A.AIRCONDITION, A.FAN, A.WBST, A.CNT_30, A.CNT_31, A.CNT_32, A.CNT_MW, A.RADIO_VENDOR, A.CNT_ROUTER )
        --TLND-1822 Zeiteinschränkung auf x Tage
        and     A.AS_START between dvon and dbis
        --and     A.STO_ID      = '342991157'
        --        and    a.sto_id in ('353990700', '457990100','468990177','468990797')
        --        and    A.STO_ID               in (select site_id from ER_REPORTING.OSS_EXAMPLES b  )
        and    rownum                 < 500000
        ;

       v_c         := sql%Rowcount;
       v_count     := v_count + v_c ;

       insert into OSS_LOG values (0, 'Debug', 'Update_rest: including budget calculation: ' || v_c || ' ' || v_count,sysdate,null);
       commit;
       
       IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_rest') 
         THEN
           insert into OSS_LOG values (0, 'Info', 'Update_rest: exit wegen Zeit' ,sysdate,null);
           commit;
           RETURN;
       END IF;

       EXIT WHEN v_c < 1;
     END LOOP;
     
     insert into OSS_LOG values (0, 'Info', 'Update_rest: including budget calculation: ' || v_count,sysdate,null);
     commit;
     
     --set WBST_VALID
     insert into OSS_LOG values (0, 'Info', 'Update_rest: Set WBST_VALID',sysdate,null);
     commit;
     v_c         := 0;
     v_count     := 0;
          
     LOOP
        IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_rest') 
          THEN
            insert into OSS_LOG values (0, 'Info', 'Update_rest: exit wegen Zeit' ,sysdate,null);
            exit;
        END IF;
        update ER_REPORTING.ER_SM_DAILY_SO_DATA a
        set    A.WBST_VALID = ER_REPORTING.OSS_ER_FUNCTION.GET_WBST_VALID(A.CNT_30,A.CNT_31,A.CNT_32,A.CNT_33,A.WBST)
        where  A.METER_VALID  = -1
        --nvl 1: weil wenn NULL dann auf jeden Fall neu berechnen
        and    nvl(A.WBST_VALID,1)   <> ER_REPORTING.OSS_ER_FUNCTION.GET_WBST_VALID(A.CNT_30,A.CNT_31,A.CNT_32,A.CNT_33,A.WBST)
        --TLND-1822 Zeiteinschränkung auf 5 Tage
        and     A.AS_START between dvon and dbis
        --and     A.STO_ID      = '342991157'
        --        and    a.sto_id in ('353990700', '457990100','468990177','468990797')
        --        and    A.STO_ID               in (select site_id from ER_REPORTING.OSS_EXAMPLES b  )
        and    rownum         < 500000
        ;

       v_c         := sql%Rowcount;
       v_count     := v_count + v_c ;

       insert into OSS_LOG values (0, 'Debug', 'Update_rest: Set WBST_VALID: ' || v_c || ' ' || v_count,sysdate,null);
       commit;
       
       IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_rest') 
         THEN
           insert into OSS_LOG values (0, 'Info', 'Update_rest: exit wegen Zeit' ,sysdate,null);
           commit;
           RETURN;
       END IF;

       EXIT WHEN v_c < 1;
     END LOOP;
     
     insert into OSS_LOG values (0, 'Info', 'Update_rest: Set WBST_VALID: ' || v_count,sysdate,null);
     commit; 

     --04.03.2022 Thomas Rochlitz; TLND-1871 Update mit TT_STD Wetter Daten
     insert into OSS_LOG values (0, 'Info', 'Update_rest: Set TT_STD',sysdate,null);
     commit;
     v_count     := 0;


     IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_rest') 
       THEN
         insert into OSS_LOG values (0, 'Info', 'Update_rest: exit wegen Zeit' ,sysdate,null);
         RETURN;
     END IF;

     FOR i_DWD IN C_DWD_WETTERDATEN LOOP
       UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA w
       SET    w.tt_std   = i_DWD.TT_STD
       where  W.STO_ID   = i_dwd.sto_id
       and    w.as_start = i_dwd.as_start
       and    w.tt_std   is null
       ;
     
       v_count     := v_count     + sql%Rowcount;
       v_count_all := v_count_all + sql%Rowcount;
           
       --commit alle x Datensätze
        --if mod(v_count, 10000) = 0 
        if v_count > 100000 
          THEN
            insert into OSS_LOG values (0, 'Debug', 'Update_rest: Set TT_STD: update: ' || v_count  || ' ' || v_count_all,sysdate,null);
            commit;
            v_count := 0;

            IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_rest') 
              THEN
                insert into OSS_LOG values (0, 'Info', 'Update_rest: Set TT_STD: exit wegen Zeit' ,sysdate,null);
                EXIT;
            END IF;
        end if;
     
     
     END LOOP i_DWD;


     insert into OSS_LOG values (0, 'Info', 'Update_rest: Ende TT_STD: update: ' || v_count  || ' ' || v_count_all,sysdate,null);
     commit;

     insert into OSS_LOG values (0, 'Performance', 'Update_rest: Ende (' || dvon || '-' || dbis || ')',sysdate,null);
     commit;
     
   END Update_rest;
   
   

   PROCEDURE Update_Tmp IS
   --tmp große Updates
     v_c     number     := 0;
     v_count number     := 0;
     v_count_all number := 0;

   BEGIN
     insert into OSS_LOG values (0, 'Performance', 'Update_Tmp: Start',sysdate,null);
     commit;
     IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_Tmp') 
       THEN
         insert into OSS_LOG values (0, 'Info', 'Update_Tmp: exit wegen Zeit' ,sysdate,null);
         RETURN;
     END IF;


     --INSERT
     --01 Daten Übernehmen und anfügen
     --ER_REPORTING.OSS_ER_FUNCTION.INSERT_ER_SM_DAILY_SO_DATA;
     --
     --UPDATE
     --02 auf veränderte Power Daten prüfen und Update machen
     --ER_REPORTING.OSS_ER_FUNCTION.UPDATE_ER_SM_DAILY_SO_DATA;
     --03 die Traffic Daten nach Technologie übertragen
     --ER_REPORTING.OSS_ER_FUNCTION.Update_Traffic_2345G (sysdate - 300, sysdate + 1 - 6);
     --04 die Traffic Daten nach Frequenzband übertragen
     --ER_REPORTING.OSS_ER_FUNCTION.Update_Traffic_FB (sysdate - 300, sysdate + 1 - 6);
     --05 die MyCom Power Daten übertragen
     --ER_REPORTING.OSS_ER_FUNCTION.UPDATE_MYCOM_COUNTER (to_date('01.01.2022','DD.MM.YYYY'), to_date('25.03.2022','DD.MM.YYYY'));
     --06 NE_TYP
     --ER_REPORTING.OSS_ER_FUNCTION.UPDATE_NE_TYP (sysdate - 5, sysdate);
     --ER_REPORTING.OSS_ER_FUNCTION.UPDATE_NE_TYP (to_date('01.01.2022','DD.MM.YYYY'), to_date('25.03.2022','DD.MM.YYYY'));
     --ER_REPORTING.OSS_ER_FUNCTION.UPDATE_NE_TYP (sysdate - 300, sysdate + 1 - 6);
     --07 MW
     --ER_REPORTING.OSS_ER_FUNCTION.UPDATE_MW (to_date('01.01.2022','DD.MM.YYYY'), to_date('25.03.2022','DD.MM.YYYY'));
     --
     --Rest
     --ER_REPORTING.OSS_ER_FUNCTION.Update_rest (sysdate - 10, sysdate);
     --ER_REPORTING.OSS_ER_FUNCTION.Update_rest(sysdate - 50, sysdate - 5);
     --ER_REPORTING.OSS_ER_FUNCTION.Update_rest(to_date('01.01.2022','DD.MM.YYYY'), to_date('25.03.2022','DD.MM.YYYY'));
     --Update tables
     --ER_REPORTING.OSS_ER_FUNCTION.Update_day_month_table;
     
     --ER_REPORTING.OSS_ER_FUNCTION.MAKE_HIS;
     
     LOOP

        delete ER_REPORTING.ER_SM_DAILY_SO_DATA a
        --select *
        --from   ER_REPORTING.ER_SM_DAILY_SO_DATA a
        where  substr(a.SERIALNUMBER,0,1) <> '6'
        and    rownum < 100000
        ;


       v_c         := sql%Rowcount;
       v_count     := v_c;
       v_count_all := v_count_all + v_c;
       
       insert into OSS_LOG values (0, 'Debug', 'Update_Tmp: ' || v_c || ' ' || v_count_all,sysdate,null);
       commit;
       
       IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_Tmp') 
         THEN
           insert into OSS_LOG values (0, 'Info', 'Update_Tmp: exit wegen Zeit' ,sysdate,null);
           commit;
           RETURN;
       END IF;

       EXIT WHEN v_c < 1;
     END LOOP;
     
     --insert into OSS_LOG values (0, 'Info', 'Update_Tmp: update: ' || v_count ,sysdate,null);
     insert into OSS_LOG values (0, 'Info', 'Update_Tmp: Ende' || v_count_all ,sysdate,null);
     commit;     
   
   END Update_Tmp ; 
   
   
   PROCEDURE DAILY_JOB IS
     --fasst alle Jobs des Tabes zusammen
     --TLND-1822: separation: short term, long term

   BEGIN  
     --INSERT
     --01 Daten Übernehmen und anfügen
     ER_REPORTING.OSS_ER_FUNCTION.INSERT_ER_SM_DAILY_SO_DATA2;
     ER_REPORTING.OSS_ER_FUNCTION.UPDATE_ER_SM_DAILY_GENERAL;
     
     --UPDATE
     --02 auf veränderte Power Daten prüfen und Update machen
     --### tmp wegen Tmp segment ER_REPORTING.OSS_ER_FUNCTION.UPDATE_ER_SM_DAILY_SO_DATA;
     --03 die Traffic Daten nach Technologie übertragen
     ER_REPORTING.OSS_ER_FUNCTION.Update_Traffic_2345G (sysdate - 5, sysdate);
     --04 die Traffic Daten nach Frequenzband übertragen
     ER_REPORTING.OSS_ER_FUNCTION.Update_Traffic_FB (sysdate - 5, sysdate);
     --05 die MyCom Power Daten übertragen
     ER_REPORTING.OSS_ER_FUNCTION.UPDATE_MYCOM_COUNTER (sysdate - 5, sysdate);
     --06 NE_TYP
     ER_REPORTING.OSS_ER_FUNCTION.UPDATE_NE_TYP (sysdate - 5, sysdate);
     --07 MW
     ER_REPORTING.OSS_ER_FUNCTION.UPDATE_MW (sysdate - 5, sysdate);
     
     --08 Rest
     ER_REPORTING.OSS_ER_FUNCTION.Update_rest (sysdate - 5, sysdate);
     --Update tables -> extra Job
     --ER_REPORTING.OSS_ER_FUNCTION.Update_day_month_table;
     
   END DAILY_JOB;
   
   PROCEDURE Update_MyCom_Counter (dvon date, dbis date) IS
   --überträgt die Counter aus MyCom in die Zieltabelle
   --10.10.2022 Christian Kulitza: TLND-1938 updated procedure Update_MyCom_Counter to use lookup for STO_ID exclusion
   --10.10.2022 Christian Kulitza: TLND-1938 updated procedure Update_MyCom_Counter: dependency table oss_sto_id_exclude
   --10.10.2022 Christian Kulitza: TLND-1938 updated procedure Update_MyCom_Counter: dependency view OSS_V_STO_ID_EXCLUDE
   --12.10.2022 Christian Kulitza: TLND-1938 updated to use function get_sto_ids instead of view with the statement below
   --select * from table(get_sto_ids(10, 80))
   --12.10.2022 Christian Kulitza: TLND-1938 updated to use function get_sto_ids_date instead of view with the statement below
   --select * from table(get_sto_ids_date(sysdate-10, sysdate-80)) 
    v_c     number     := 0;
    v_count number     := 0;
    v_count_all number := 0;
    cursor c_p2G is 
        select T.ID, T.STO_ID, T.AS_START, T.RAD2_VERBRAUCH_BTSBOARD_KW, T.RAD2_VERBRAUCH_BU_KW
        ,      MY2.VS_ENERGYCONS_BTSBOARD, MY2.VS_ENERGYCONS_BU
        from   ER_REPORTING.MYC_COUNTER_2G_POWER_HOURLY my2
        ,      ER_REPORTING.ER_SM_DAILY_SO_DATA t
        where  MY2.AS_START = T.AS_START
        and    MY2.STO_ID   = T.STO_ID
        and    T.RAD2_VERBRAUCH_BTSBOARD_KW is null
        and    MY2.NE_STATE = 'Active'
        --Ausschluss STO Mit mehr als einem SM
        and    t.sto_id   not in (select distinct sto_id from ER_REPORTING.ER_SM_DAILY_SO_DATA eind group by EIND.STO_ID, EIND.AS_START having count(*) > 1  )
        --Ausschluss STO wegen falscher 3G Counter 
		--TLND-1938 Disable sites for MyCom power consumption
        --and    t.sto_id   not in (select * from OSS_V_STO_ID_EXCLUDE)
        and    t.sto_id   not in (select * from table(get_sto_ids_date(dvon, dbis)))
        --TLND-1822 Update_MyCom_Counter Zeiteinschränkung auf 5 Tage
        and    T.AS_START   between dvon AND dbis
        and    MY2.AS_START between dvon AND dbis        
        --Test Menge
        and    rownum <= 5000000
        ;
    cursor c_p3G is 
        select T.ID, T.STO_ID, T.AS_START, T.RAD3_VERBRAUCH_BTSBOARD_KW, T.RAD3_VERBRAUCH_BU_KW
        ,      MY3.VS_ENERGYCONS_BTSBOARD, MY3.VS_ENERGYCONS_BU
        from   ER_REPORTING.MYC_COUNTER_3G_POWER_HOURLY my3
        ,      ER_REPORTING.ER_SM_DAILY_SO_DATA t
        where  MY3.AS_START = T.AS_START
        and    MY3.STO_ID   = T.STO_ID
        and    T.RAD3_VERBRAUCH_BTSBOARD_KW is null
        and    MY3.NE_STATE = 'Active'
        --Ausschluss STO Mit mehr als einem SM
        and    t.sto_id   not in (select distinct sto_id from ER_REPORTING.ER_SM_DAILY_SO_DATA eind group by EIND.STO_ID, EIND.AS_START having count(*) > 1  ) 
        --Ausschluss STO wegen falscher 3G Counter 
		--TLND-1938 Disable sites for MyCom power consumption
        --and    t.sto_id   not in (select * from OSS_V_STO_ID_EXCLUDE)
        and    t.sto_id   not in (select * from table(get_sto_ids_date(dvon, dbis)))
        --TLND-1822 Update_MyCom_Counter Zeiteinschränkung auf 5 Tage
        and    T.AS_START   between dvon AND dbis
        and    MY3.AS_START between dvon AND dbis        
        --Test Menge
        and    rownum <= 5000000
        ;
    cursor c_p4G is 
        select T.ID, T.STO_ID, T.AS_START, T.RAD4_VERBRAUCH_BTSBOARD_KW, T.RAD4_VERBRAUCH_BU_KW
        ,      MY4.VS_ENERGYCONS_BTSBOARD, MY4.VS_ENERGYCONS_BU
        from   ER_REPORTING.MYC_COUNTER_4G_POWER_HOURLY my4
        ,      ER_REPORTING.ER_SM_DAILY_SO_DATA t
        where  MY4.AS_START = T.AS_START
        and    MY4.STO_ID   = T.STO_ID
        and    T.RAD4_VERBRAUCH_BTSBOARD_KW is null
        and    MY4.NE_STATE = 'Active'
        --Ausschluss STO Mit mehr als einem SM
        and    t.sto_id   not in (select distinct sto_id from ER_REPORTING.ER_SM_DAILY_SO_DATA eind group by EIND.STO_ID, EIND.AS_START having count(*) > 1  ) 
        --Ausschluss STO wegen falscher 3G Counter 
		--TLND-1938 Disable sites for MyCom power consumption
        --and    t.sto_id   not in (select * from OSS_V_STO_ID_EXCLUDE)
        and    t.sto_id   not in (select * from table(get_sto_ids_date(dvon, dbis)))
        --TLND-1822 Update_MyCom_Counter Zeiteinschränkung auf 5 Tage
        and    T.AS_START   between dvon AND dbis
        and    MY4.AS_START between dvon AND dbis        
        --Test Menge
        and    rownum <= 5000000
        ;
    cursor c_p5G is 
        select T.ID, T.STO_ID, T.AS_START, T.RAD5_VERBRAUCH_BTSBOARD_KW, T.RAD5_VERBRAUCH_BU_KW
        ,      MY5.VS_ENERGYCONS_BTSBOARD, MY5.VS_ENERGYCONS_BU
        from   ER_REPORTING.MYC_COUNTER_5G_POWER_HOURLY my5
        ,      ER_REPORTING.ER_SM_DAILY_SO_DATA t
        where  MY5.AS_START = T.AS_START
        and    MY5.STO_ID   = T.STO_ID
        --and    ((T.RAD5_VERBRAUCH_BTSBOARD_KW is null) or (T.RAD5_VERBRAUCH_BU_KW is null ))
        and    T.RAD5_VERBRAUCH_BTSBOARD_KW is null
        and    MY5.NE_STATE = 'Active'
        --Ausschluss STO Mit mehr als einem SM
        and    t.sto_id   not in (select distinct sto_id from ER_REPORTING.ER_SM_DAILY_SO_DATA eind group by EIND.STO_ID, EIND.AS_START having count(*) > 1  ) 
        --Ausschluss STO wegen falscher 3G Counter 
		--TLND-1938 Disable sites for MyCom power consumption
        --and    t.sto_id   not in (select * from OSS_V_STO_ID_EXCLUDE)
        and    t.sto_id   not in (select * from table(get_sto_ids_date(dvon, dbis)))
        --TLND-1822 Update_MyCom_Counter Zeiteinschränkung auf 5 Tage
        and    T.AS_START   between dvon AND dbis
        and    MY5.AS_START between dvon AND dbis        
        --Test Menge
        and    rownum <= 5000000
        ;
   
   BEGIN
     insert into OSS_LOG values (0, 'Performance', 'Update_MyCom_Counter: Start (' || dvon || '-' || dbis || ')',sysdate,null);
     commit;
     IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_MyCom_Counter') 
       THEN
         insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: exit wegen Zeit' ,sysdate,null);
         RETURN;
     END IF;

     --STO_ID füllen
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: STO Update 2G',sysdate,null);
     LOOP
         update ER_REPORTING.MYC_COUNTER_2G_POWER_HOURLY m2
         set M2.STO_ID = (select NESO.STANDORT_NR_
                 from   ER_REPORTING.NETS_NESO neso
                 --genau auf Zahlen kontrollieren, da noch Buchstaben drum rum sein können
                 where  NESO.NETZELEMENT_NR_    = substr(M2.NE_ID, REGEXP_INSTR( M2.NE_ID, '[0-9]{9}', 1, 1 ), 9)
                 and    NESO.NESO_AKTIV         = 'Ja'
                 and    NESO.NETZELEMENT_AKTIV_ = 'Ja'
                )
         where  M2.STO_ID is null
         and    regexp_like (M2.NE_ID, '^(0|1|2|3|4|5|6|7|8|9)' )
         --exclude missing sites
         and    (select NESO.STANDORT_NR_
                 from   ER_REPORTING.NETS_NESO neso
                 --genau auf Zahlen kontrollieren, da noch Buchstaben drum rum sein können
                 where  NESO.NETZELEMENT_NR_    = substr(M2.NE_ID, REGEXP_INSTR( M2.NE_ID, '[0-9]{9}', 1, 1 ), 9)
                 and    NESO.NESO_AKTIV         = 'Ja'
                 and    NESO.NETZELEMENT_AKTIV_ = 'Ja'
                ) IS NOT NULL
         and    rownum                 < 500000
         ;
         v_c         := sql%Rowcount;
         v_count     := v_count + v_c ;
         insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: STO Update 2G: ' || v_c || ' ' || v_count,sysdate,null);
         commit;

         EXIT WHEN v_c < 1;
             
     END LOOP;
          
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: STO Update 3G',sysdate,null);
     update ER_REPORTING.MYC_COUNTER_3G_POWER_HOURLY m3
     set M3.STO_ID = (select NESO.STANDORT_NR_
             from   ER_REPORTING.NETS_NESO neso
             where  NESO.NETZELEMENT_NR_    = substr(M3.NE_ID, REGEXP_INSTR( M3.NE_ID, '[0-9]{9}', 1, 1 ), 9)
             and    NESO.NESO_AKTIV         = 'Ja'
             and    NESO.NETZELEMENT_AKTIV_ = 'Ja'
            )
     where  M3.STO_ID is null
     and    regexp_like (M3.NE_ID, '^(0|1|2|3|4|5|6|7|8|9)' )
     ;
     commit;
     
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: STO Update 4G',sysdate,null);
     LOOP
         update ER_REPORTING.MYC_COUNTER_4G_POWER_HOURLY m4
         set M4.STO_ID = (select NESO.STANDORT_NR_
                 from   ER_REPORTING.NETS_NESO neso
                 where  NESO.NETZELEMENT_NR_    = substr(M4.NE_ID, REGEXP_INSTR( M4.NE_ID, '[0-9]{9}', 1, 1 ), 9)
                 and    NESO.NESO_AKTIV         = 'Ja'
                 and    NESO.NETZELEMENT_AKTIV_ = 'Ja'
                )
         where  M4.STO_ID is null
         and    regexp_like (M4.NE_ID, '^(0|1|2|3|4|5|6|7|8|9)' )
         --exclude missing sites
         and    (select NESO.STANDORT_NR_
                 from   ER_REPORTING.NETS_NESO neso
                 --genau auf Zahlen kontrollieren, da noch Buchstaben drum rum sein können
                 where  NESO.NETZELEMENT_NR_    = substr(M4.NE_ID, REGEXP_INSTR( M4.NE_ID, '[0-9]{9}', 1, 1 ), 9)
                 and    NESO.NESO_AKTIV         = 'Ja'
                 and    NESO.NETZELEMENT_AKTIV_ = 'Ja'
                ) IS NOT NULL
         and    rownum                 < 500000
         ;
         v_c         := sql%Rowcount;
         v_count     := v_count + v_c ;
         insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: STO Update 4G: ' || v_c || ' ' || v_count,sysdate,null);
         commit;

         EXIT WHEN v_c < 1;
             
     END LOOP;
     
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: STO Update 5G',sysdate,null);
     LOOP
         update ER_REPORTING.MYC_COUNTER_5G_POWER_HOURLY m5
         set M5.STO_ID = (select NESO.STANDORT_NR_
                 from   ER_REPORTING.NETS_NESO neso
                 where  NESO.NETZELEMENT_NR_    = substr(M5.NE_ID, REGEXP_INSTR( M5.NE_ID, '[0-9]{9}', 1, 1 ), 9)
                 and    NESO.NESO_AKTIV         = 'Ja'
                 and    NESO.NETZELEMENT_AKTIV_ = 'Ja'
                )
         where  M5.STO_ID is null
         and    regexp_like (M5.NE_ID, '^(0|1|2|3|4|5|6|7|8|9)' )
         --ORA-01722 Ungültige Zahl, source: http://www.loiblnet.de/info/ora-01722-ungueltige-zahl/
         and    TRIM(TRANSLATE(substr(M5.NE_ID, REGEXP_INSTR( M5.NE_ID, '[0-9]{9}', 1, 1 ), 9), '1234567890', ' ')) IS NULL 
         --exclude missing sites
         and    (select NESO.STANDORT_NR_
                 from   ER_REPORTING.NETS_NESO neso
                 --genau auf Zahlen kontrollieren, da noch Buchstaben drum rum sein können
                 where  NESO.NETZELEMENT_NR_    = substr(M5.NE_ID, REGEXP_INSTR( M5.NE_ID, '[0-9]{9}', 1, 1 ), 9)
                 and    NESO.NESO_AKTIV         = 'Ja'
                 and    NESO.NETZELEMENT_AKTIV_ = 'Ja'
                ) IS NOT NULL
         and    rownum                 < 500000
         ;
         v_c         := sql%Rowcount;
         v_count     := v_count + v_c ;
         insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: STO Update 5G: ' || v_c || ' ' || v_count,sysdate,null);
         commit;

         EXIT WHEN v_c < 1;
             
     END LOOP; 
     
     --Ziel Tabelle füllen
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: Update target table',sysdate,null);
     
     --2G
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 2G',sysdate,null);
     commit;
     v_count     := 0;
     v_count_all := 0;
     for ip2g in c_p2G loop
      update ER_REPORTING.ER_SM_DAILY_SO_DATA a
      set    A.RAD2_VERBRAUCH_BTSBOARD_KW = nvl(ip2g.VS_ENERGYCONS_BTSBOARD,0)
      ,      A.RAD2_VERBRAUCH_BU_KW       = nvl(ip2g.VS_ENERGYCONS_BU,0)
      where  A.ID = ip2g.ID;
      
      update ER_REPORTING.ER_SM_DAILY_SO_DATA a
      set    A.RADIO_VERBRAUCH_KW = nvl(A.RAD2_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD2_VERBRAUCH_BU_KW ,0) 
                                  + nvl(A.RAD3_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD3_VERBRAUCH_BU_KW ,0) 
                                  --+ nvl(A.RAD4_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD4_VERBRAUCH_BU_KW ,0) 
                                  + nvl(A.RAD5_VERBRAUCH_BTSBOARD_KW ,nvl(A.RAD4_VERBRAUCH_BTSBOARD_KW, 0)) + nvl(A.RAD5_VERBRAUCH_BU_KW ,nvl(A.RAD4_VERBRAUCH_BU_KW ,0))
      where  A.ID = ip2g.ID
      ;
      
      v_count     := v_count     + sql%Rowcount;
      v_count_all := v_count_all + sql%Rowcount;
      --commit alle x Datensätze
       if mod(v_count, 100000) = 0 
         THEN
           insert into OSS_LOG values (0, 'Debug', 'Update_MyCom_Counter.Update target table 2G: update: ' || v_count || ' (' || v_count_all || ')',sysdate,null);
           commit;
           v_count := 0;
     
           IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_MyCom_Counter') 
             THEN
               insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 2G: exit wegen Zeit' ,sysdate,null);
               RETURN;
           END IF;
           
       end if;
     end loop it2g;
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 2G: Ende ' || v_count  || ' (' || v_count_all || ')',sysdate,null);
      
     --3G
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 3G',sysdate,null);
     commit;
     v_count := 0;
     for ip3g in c_p3G loop
      update ER_REPORTING.ER_SM_DAILY_SO_DATA a
      set    A.RAD3_VERBRAUCH_BTSBOARD_KW = nvl(ip3g.VS_ENERGYCONS_BTSBOARD,0)
      ,      A.RAD3_VERBRAUCH_BU_KW       = nvl(ip3g.VS_ENERGYCONS_BU,0)
      where  A.ID = ip3g.ID;
      
      update ER_REPORTING.ER_SM_DAILY_SO_DATA a
      set    A.RADIO_VERBRAUCH_KW = nvl(A.RAD2_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD2_VERBRAUCH_BU_KW ,0) 
                                  + nvl(A.RAD3_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD3_VERBRAUCH_BU_KW ,0) 
                                  --+ nvl(A.RAD4_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD4_VERBRAUCH_BU_KW ,0) 
                                  + nvl(A.RAD5_VERBRAUCH_BTSBOARD_KW ,nvl(A.RAD4_VERBRAUCH_BTSBOARD_KW, 0)) + nvl(A.RAD5_VERBRAUCH_BU_KW ,nvl(A.RAD4_VERBRAUCH_BU_KW ,0))
      where  A.ID = ip3g.ID;
      
      v_count     := v_count     + sql%Rowcount;
      v_count_all := v_count_all + sql%Rowcount;
      --commit alle x Datensätze
       if mod(v_count, 100000) = 0 
         THEN
           insert into OSS_LOG values (0, 'Debug', 'Update_MyCom_Counter.Update target table 3G: update: ' || v_count || ' (' || v_count_all || ')' ,sysdate,null);
           commit;
           v_count := 0;
     
           IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_MyCom_Counter') 
             THEN
               insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 3G: exit wegen Zeit' ,sysdate,null);
               RETURN;
           END IF;
           
       end if;
     end loop it3g;
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 3G: Ende ' || v_count  || ' (' || v_count_all || ')',sysdate,null);
      
     --4G
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 4G',sysdate,null);
     commit;
     v_count := 0;
     for ip4g in c_p4G loop
      update ER_REPORTING.ER_SM_DAILY_SO_DATA a
      set    A.RAD4_VERBRAUCH_BTSBOARD_KW = nvl(ip4g.VS_ENERGYCONS_BTSBOARD,0)
      ,      A.RAD4_VERBRAUCH_BU_KW       = nvl(ip4g.VS_ENERGYCONS_BU,0)
      where  A.ID = ip4g.ID;
      
      update ER_REPORTING.ER_SM_DAILY_SO_DATA a
      set    A.RADIO_VERBRAUCH_KW = nvl(A.RAD2_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD2_VERBRAUCH_BU_KW ,0) 
                                  + nvl(A.RAD3_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD3_VERBRAUCH_BU_KW ,0) 
                                  --+ nvl(A.RAD4_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD4_VERBRAUCH_BU_KW ,0) 
                                  + nvl(A.RAD5_VERBRAUCH_BTSBOARD_KW ,nvl(A.RAD4_VERBRAUCH_BTSBOARD_KW, 0)) + nvl(A.RAD5_VERBRAUCH_BU_KW ,nvl(A.RAD4_VERBRAUCH_BU_KW ,0))
      where  A.ID = ip4g.ID;
      
      v_count     := v_count     + sql%Rowcount;
      v_count_all := v_count_all + sql%Rowcount;
      --commit alle x Datensätze
       if mod(v_count, 100000) = 0 
         THEN
           insert into OSS_LOG values (0, 'Debug', 'Update_MyCom_Counter.Update target table 4G: update: ' || v_count || ' (' || v_count_all || ')' ,sysdate,null);
           commit;
           v_count := 0;
     
           IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_MyCom_Counter') 
             THEN
               insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 4G: exit wegen Zeit' ,sysdate,null);
               RETURN;
           END IF;
           
       end if;
     end loop it4g;
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 4G: Ende ' || v_count || ' (' || v_count_all || ')' ,sysdate,null);
      
     
     --5G
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 5G',sysdate,null);
     commit;
     v_count := 0;
     for ip5g in c_p5G loop
      update ER_REPORTING.ER_SM_DAILY_SO_DATA a
      set    A.RAD5_VERBRAUCH_BTSBOARD_KW = nvl(ip5g.VS_ENERGYCONS_BTSBOARD,0)
      ,      A.RAD5_VERBRAUCH_BU_KW       = nvl(ip5g.VS_ENERGYCONS_BU,0)
      where  A.ID = ip5g.ID;
      
      update ER_REPORTING.ER_SM_DAILY_SO_DATA a
      set    A.RADIO_VERBRAUCH_KW = nvl(A.RAD2_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD2_VERBRAUCH_BU_KW ,0) 
                                  + nvl(A.RAD3_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD3_VERBRAUCH_BU_KW ,0) 
                                  --+ nvl(A.RAD4_VERBRAUCH_BTSBOARD_KW ,0) + nvl(A.RAD4_VERBRAUCH_BU_KW ,0) 
                                  + nvl(A.RAD5_VERBRAUCH_BTSBOARD_KW ,nvl(A.RAD4_VERBRAUCH_BTSBOARD_KW, 0)) + nvl(A.RAD5_VERBRAUCH_BU_KW ,nvl(A.RAD4_VERBRAUCH_BU_KW ,0))
      where  A.ID = ip5g.ID;
      
      v_count     := v_count     + sql%Rowcount;
      v_count_all := v_count_all + sql%Rowcount;
      --commit alle x Datensätze
       if mod(v_count, 100000) = 0 
         THEN
           insert into OSS_LOG values (0, 'Debug', 'Update_MyCom_Counter.Update target table 5G: update: ' || v_count || ' ()' || v_count_all || ')' ,sysdate,null);
           commit;
            v_count := 0;
    
           IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_MyCom_Counter') 
             THEN
               insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 5G: exit wegen Zeit' ,sysdate,null);
               RETURN;
           END IF;
           
       end if;
     end loop it5g;
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table 5G: Ende ' || v_count || ' (' || v_count_all || ')' ,sysdate,null);

     --ALL_ Attribute füllen
     --Schleife bis 0 einbauen, um Langläufer zu beachten
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: ALL_TRAFFIC_VS_POWER_MB_KW: Start',sysdate,null);
     LOOP
       UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA a
       set    a.ALL_TRAFFIC_VS_POWER_MB_KW = a.ALL_TRAFFIC_MB / a.VERBRAUCH_HT_NT
       where  a.VERBRAUCH_HT_NT            > 0   --später auf 0
       and    a.all_traffic_mb             is not null 
       and    round(nvl(a.ALL_TRAFFIC_VS_POWER_MB_KW,0)) != round(a.ALL_TRAFFIC_MB / a.VERBRAUCH_HT_NT)
       --a.ALL_TRAFFIC_VS_POWER_MB_KW is null
       --and    a.VERBRAUCH_HT_NT            > 0   --später auf 0
       --and    ((a.all_traffic_mb             is not null) OR
       --        (round(nvl(a.ALL_TRAFFIC_VS_POWER_MB_KW,0)) != round(a.ALL_TRAFFIC_MB / a.VERBRAUCH_HT_NT))) 
       --TLND-1822 Update_MyCom_Counter Zeiteinschränkung auf 5 Tage
       and    a.AS_START   between dvon AND dbis
       and    rownum                       < 500000;
       v_c         := sql%Rowcount;
       v_count     := v_count + v_c ;
       v_count_all := v_count_all + v_c;
       insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: ALL_TRAFFIC_VS_POWER_MB_KW: Update ' || v_count || ' v_c: ' || v_c ,sysdate,null);
       commit;
       
       IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_MyCom_Counter') 
         THEN
           insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: ALL_TRAFFIC_VS_POWER_MB_KW: exit wegen Zeit' ,sysdate,null);
           RETURN;
       END IF;

       EXIT WHEN v_c < 1;
     END LOOP;
     

     --Schleife bis 0 einbauen, um Langläufer zu beachten
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: ALL_TRAFFIC_VS_RAD_POWER_MB_KW: Start',sysdate,null);
     LOOP
       UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA a
       set    a.ALL_TRAFFIC_VS_RAD_POWER_MB_KW = a.ALL_TRAFFIC_MB / A.RADIO_VERBRAUCH_KW
       where  a.RADIO_VERBRAUCH_KW         > 0   --später auf 0
       and    a.all_traffic_mb             is not null 
       and    round(nvl(a.ALL_TRAFFIC_VS_RAD_POWER_MB_KW,0)) != round(a.ALL_TRAFFIC_MB / a.RADIO_VERBRAUCH_KW)  
       --a.ALL_TRAFFIC_VS_RAD_POWER_MB_KW is null
       --and    a.RADIO_VERBRAUCH_KW             > 0   --später auf 0
       --and    ((a.all_traffic_mb                 is not null) OR
       --        (round(nvl(a.ALL_TRAFFIC_VS_RAD_POWER_MB_KW,0)) != round(a.ALL_TRAFFIC_MB / a.RADIO_VERBRAUCH_KW))) 
       --TLND-1822 Update_MyCom_Counter Zeiteinschränkung auf 5 Tage
       and    a.AS_START   between dvon AND dbis
       and    rownum                           < 500000;

       v_c         := sql%Rowcount;
       v_count     := v_count + v_c ;
       v_count_all := v_count_all + v_c;

       insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: ALL_TRAFFIC_VS_RAD_POWER_MB_KW: Update ' || v_count || ' v_c: ' || v_c,sysdate,null);
       commit;
       
       IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_MyCom_Counter') 
         THEN
           insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: ALL_TRAFFIC_VS_RAD_POWER_MB_KW: exit wegen Zeit' ,sysdate,null);
           RETURN;
       END IF;

       EXIT WHEN v_c < 1;
     END LOOP;
     
      
     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter.Update target table All: Ende  (' || dvon || '-' || dbis || ') ' || v_count_all ,sysdate,null);
--     insert into OSS_LOG values (0, 'Info', 'Update_MyCom_Counter: Ende',sysdate,null);
     commit;
     
   END Update_MyCom_Counter;

   FUNCTION Procedure_stop (t_procedure varchar2) 
     RETURN BOOLEAN IS
   --prüft, ob die Laufzeit erreicht wurde und liefert TRUE, sonst FALSE
   n_count     NUMBER;
   d_stop_date date;
   BEGIN
    select count(*) into n_count
    from   er_reporting.oss_ende a
    where  a.PROCEDURE = t_procedure;
    IF n_count > 0 
      THEN
        --d_stop_date
        select a.stop_date into d_stop_date 
        from   er_reporting.oss_ende a
        where  a.PROCEDURE = t_procedure;
        IF d_stop_date < sysdate 
          THEN
            --Ende Zeit erreicht
            RETURN TRUE;
          ELSE
            --Ende Zeit noch NICHT erreicht
            RETURN FALSE;  
        END IF;
      ELSE
       --Parameter nicht gefunden
       RETURN FALSE;
    END IF;
   END Procedure_stop;  


   PROCEDURE Update_Traffic_2345G (dvon date, dbis date) IS
   --Übernimmt aus TUX Traffic + EQT Zellen den Traffic je nach Technologie in leere Attribute
    v_c     number     := 0;
    v_count number     := 0;
    v_count_all number := 0;
    d_stop_date date;
    cursor c_t2G is 
        select traffic2G.*
        from (select  CELL2G.LOCATION_BASEKEY STO_ID, T2G.ZEIT
              ,count(T2G.CELL) Cell
              ,sum( NULL) AVERAGE_ACTIVE_USERS
              ,(sum(T2G.TRAFFIC_VOLUME_DL_DATA_MB_2G) + sum(T2G.TRAFFIC_VOLUME_UL_DATA_MB_2G)) TRAFFIC_VOLUME_DATA_MB
              ,sum(T2G.TCH_TRAFFIC_ERL_2G) TRAFFIC_VOLUME_ERL
              from   ER_REPORTING.TUX_2G_CELL_VOL T2G
                     JOIN (select *
                           from    ER_REPORTING.EQT_V_IF_74_OI_2 ec2
                           where   (length(ec2.LAC) > 0) AND (length(ec2.SAC) IS NULL)  AND (length(ec2.RAC) > 0) 
                          ) cell2G ON T2G.CELL = CELL2G.CELL_BASEKEY  --.CELL_BASEKEY
            --where CELL2G.LOCATION_BASEKEY = '239990734'
            group by CELL2G.LOCATION_BASEKEY , T2G.ZEIT
            ) traffic2G
         ,  ER_REPORTING.ER_SM_DAILY_SO_DATA er_target   
         where  er_target.sto_id   = traffic2G.sto_id
         and    er_target.as_start = traffic2g.ZEIT
         and    er_target.t2g_cell is null
         and    traffic2g.cell is not null
         --Test Menge
         and    rownum <= 5000000
         --Zeiteinschränkung
         and    traffic2g.zeit between dvon AND dbis
         --and    er_target.sto_id in (select site_id from ER_REPORTING.OSS_EXAMPLES )
         --30.01.2022 Thomas Rochlitz, entfernen von Order By
         --order by ZEIT desc
         ;
    cursor c_t3G is 
        select traffic3G.*
        from (select CELL3G.LOCATION_BASEKEY STO_ID,  T3G.ZEIT ZEIT
              ,count(T3G.FDDCELL) CELL 
              ,sum(NULL) AVERAGE_ACTIVE_USERS
              ,sum(nvl(T3G.HSDPA_TRAFFIC_MB_3G,0)) + sum(nvl(T3G.HSUPA_TRAFFIC_MB_3G,0)) + 
               sum(nvl(T3G.REL99_PS_DL_DCH_TRAFFIC_MB_3G ,0)) + sum(nvl(T3G.REL99_PS_UL_DCH_TRAFFIC_MB_3G ,0))   TRAFFIC_VOLUME_DATA_MB
              ,sum(T3G.VOICE_TRAFFIC_FDDCELL_ERL_3G) TRAFFIC_VOLUME_ERL
              from   ER_REPORTING.TUX_3G_CELL_VOL T3G
                     JOIN (select *
                           from    ER_REPORTING.EQT_V_IF_74_OI_2 ec3
                           where   (length(ec3.LAC) > 0) AND (length(ec3.RAC) > 0)  AND (length(ec3.SAC) > 0) 
                          ) cell3G ON T3G.FDDCELL = CELL3G.CELL_BASEKEY  --.CELL_BASEKEY
            group by CELL3G.LOCATION_BASEKEY , T3G.ZEIT
            ) traffic3G
         ,  ER_REPORTING.ER_SM_DAILY_SO_DATA er_target   
         where  er_target.sto_id   = traffic3G.sto_id
         and    er_target.as_start = traffic3G.ZEIT
         and    er_target.t3g_cell is null
         and    traffic3g.cell is not null
         --Test Menge
         and    rownum <= 5000000
         --Zeiteinschränkung
         and    traffic3g.zeit between dvon AND dbis
         --and    er_target.sto_id in (select site_id from ER_REPORTING.OSS_EXAMPLES )
         --order by ZEIT desc
         ;

    cursor c_t4G is 
        select traffic4G.*
        from (select CELL4G.LOCATION_BASEKEY STO_ID,  T4G.ZEIT ZEIT
              ,count(T4G.ECELL ) CELL 
              ,sum(T4G.AVERAGE_ACTIVE_USERS_4G ) AVERAGE_ACTIVE_USERS
              ,sum(nvl(T4G.TRAFFIC_VOLUME_DL_DATA_MB_4G ,0)) + sum(nvl(T4G.TRAFFIC_VOLUME_UL_DATA_MB_4G ,0)) TRAFFIC_VOLUME_DATA_MB
              ,sum(T4G.TRAFFIC_VOLUME_VOICE_ERL_4G ) TRAFFIC_VOLUME_ERL
              from   ER_REPORTING.TUX_4G_CELL_VOL T4G
                     JOIN (select *
                           from    ER_REPORTING.EQT_V_IF_74_OI_2 ec4
                           where   (length(ec4.ECI) > 0) 
                          ) cell4G ON T4G.ECELL = CELL4G.CELL_BASEKEY  --.CELL_BASEKEY
            group by CELL4G.LOCATION_BASEKEY , T4G.ZEIT
            ) traffic4G
         ,  ER_REPORTING.ER_SM_DAILY_SO_DATA er_target   
         where  er_target.sto_id   = traffic4G.sto_id
         and    er_target.as_start = traffic4G.ZEIT
         and    er_target.t4g_cell is null
         and    traffic4g.cell is not null
         --Test Menge
         and    rownum <= 3000000
         --Zeiteinschränkung
         and    traffic4g.zeit between dvon AND dbis
         --and    er_target.sto_id in (select site_id from ER_REPORTING.OSS_EXAMPLES )
         --order by ZEIT desc
         ;

    cursor c_t5G is 
        select traffic5G.*
        from (select CELL5G.LOCATION_BASEKEY STO_ID,  T5G.ZEIT ZEIT
              ,count(T5G.NRCELL ) CELL 
              ,sum(T5G.AVERAGE_ACTIVE_USERS_5G ) AVERAGE_ACTIVE_USERS
              ,sum(nvl(T5G.TRAFFIC_VOLUME_DL_DATA_MB_5G ,0)) + sum(nvl(T5G.TRAFFIC_VOLUME_UL_DATA_MB_5G ,0)) TRAFFIC_VOLUME_DATA_MB
              ,sum(NULL ) TRAFFIC_VOLUME_ERL
              from   ER_REPORTING.TUX_5G_CELL_VOL T5G
                     JOIN (select *
                           from    ER_REPORTING.EQT_V_IF_74_OI_2 ec5
                           where   (length(ec5.NCI) > 0)  
                          ) cell5G ON T5G.NRCELL = CELL5G.CELL_BASEKEY  --.CELL_BASEKEY
            group by CELL5G.LOCATION_BASEKEY , T5G.ZEIT
            ) traffic5G
         ,  ER_REPORTING.ER_SM_DAILY_SO_DATA er_target   
         where  er_target.sto_id   = traffic5G.sto_id
         and    er_target.as_start = traffic5G.ZEIT
         and    er_target.t5g_cell is null
         and    traffic5g.cell is not null
         --Test Menge
         and    rownum <= 5000000
         --Zeiteinschränkung
         and    traffic5g.zeit between dvon AND dbis
         --and    er_target.sto_id in (select site_id from ER_REPORTING.OSS_EXAMPLES )
         --order by ZEIT desc
         ;
   BEGIN
      insert into OSS_LOG values (0, 'Performance', 'Update_Traffic_2345G: Start (' || dvon || '-' || dbis || ')',sysdate,null);
      commit;
      IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_Traffic_2345G') 
        THEN
          insert into OSS_LOG values (0, 'Info', 'Traffic 2G: exit wegen Zeit' ,sysdate,null);
          RETURN;
      END IF;
        
      for it2g in c_t2G loop
       update ER_REPORTING.ER_SM_DAILY_SO_DATA a
       set    T2G_cell                  = nvl(it2g.cell, 0)
       ,      TRAFFIC_VOLUME_DATA_MB_2G = nvl(it2g.TRAFFIC_VOLUME_DATA_MB, 0)
       ,      TRAFFIC_VOLUME_ERL_2G     = nvl(it2g.TRAFFIC_VOLUME_ERL, 0)
       where  a.sto_id   = it2g.sto_id
         and  a.as_start = it2g.ZEIT
       ;
       v_count     := v_count     + sql%Rowcount;
       v_count_all := v_count_all + sql%Rowcount;
       --commit alle x Datensätze
        if mod(v_count, 100000) = 0 
          THEN
            insert into OSS_LOG values (0, 'Debug', 'Traffic 2G: update: ' || v_count ,sysdate,null);
            commit;
       
            IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_Traffic_2345G') 
              THEN
                insert into OSS_LOG values (0, 'Info', 'Traffic 2G: exit wegen Zeit' ,sysdate,null);
                RETURN;
            END IF;
            
        end if;
      end loop it2g;
      insert into OSS_LOG values (0, 'Info', 'Traffic 2G: Ende ' || v_count ,sysdate,null);
      
      --3G
      insert into OSS_LOG values (0, 'Info', 'Traffic 3G: Start',sysdate,null);
      commit;
      v_count := 0;
      for it3g in c_t3G loop
       update ER_REPORTING.ER_SM_DAILY_SO_DATA a
       set    T3G_cell                  = nvl(it3g.cell, 0)
       ,      TRAFFIC_VOLUME_DATA_MB_3G = nvl(it3g.TRAFFIC_VOLUME_DATA_MB, 0)
       ,      TRAFFIC_VOLUME_ERL_3G     = nvl(it3g.TRAFFIC_VOLUME_ERL, 0)
       where  a.sto_id   = it3g.sto_id
         and  a.as_start = it3g.ZEIT
       ;
       v_count     := v_count     + sql%Rowcount;
       v_count_all := v_count_all + sql%Rowcount;
       --commit alle x Datensätze
        if mod(v_count, 100000) = 0 
          THEN
            insert into OSS_LOG values (0, 'Debug', 'Traffic 3G: update: ' || v_count ,sysdate,null);
            commit;
       
            IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_Traffic_2345G') 
              THEN
                insert into OSS_LOG values (0, 'Info', 'Traffic 3G: exit wegen Zeit' ,sysdate,null);
                RETURN;
            END IF;
            
        end if;
      end loop it3g;
      insert into OSS_LOG values (0, 'Info', 'Traffic 3G: Ende ' || v_count ,sysdate,null);
      
      --4G
      insert into OSS_LOG values (0, 'Info', 'Traffic 4G: Start',sysdate,null);
      commit;
      v_count := 0;
      for it4g in c_t4G loop
       update ER_REPORTING.ER_SM_DAILY_SO_DATA a
       set    T4G_cell                  = nvl(it4g.cell, 0)
       ,      AVERAGE_ACTIVE_USERS_4G   = nvl(it4g.AVERAGE_ACTIVE_USERS, 0 )
       ,      TRAFFIC_VOLUME_DATA_MB_4G = nvl(it4g.TRAFFIC_VOLUME_DATA_MB, 0)
       ,      TRAFFIC_VOLUME_ERL_4G     = nvl(it4g.TRAFFIC_VOLUME_ERL, 0)
       where  a.sto_id   = it4g.sto_id
         and  a.as_start = it4g.ZEIT
       ;
       v_count     := v_count     + sql%Rowcount;
       v_count_all := v_count_all + sql%Rowcount;
       --commit alle x Datensätze
        if mod(v_count, 100000) = 0 
          THEN
            insert into OSS_LOG values (0, 'Debug', 'Traffic 4G: update: ' || v_count ,sysdate,null);
            commit;
       
            IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_Traffic_2345G') 
              THEN
                insert into OSS_LOG values (0, 'Info', 'Traffic 4G: exit wegen Zeit' ,sysdate,null);
                RETURN;
            END IF;
            
        end if;
      end loop it4g;
      insert into OSS_LOG values (0, 'Info', 'Traffic 4G: Ende ' || v_count ,sysdate,null);
      
      --5G
      insert into OSS_LOG values (0, 'Info', 'Traffic 5G: Start',sysdate,null);
      commit;
      v_count := 0;
      for it5g in c_t5G loop
       update ER_REPORTING.ER_SM_DAILY_SO_DATA a
       set    T5G_cell                  = nvl(it5g.cell, 0)
       ,      AVERAGE_ACTIVE_USERS_5G   = nvl(it5g.AVERAGE_ACTIVE_USERS, 0 )
       ,      TRAFFIC_VOLUME_DATA_MB_5G = nvl(it5g.TRAFFIC_VOLUME_DATA_MB, 0)
       --,      TRAFFIC_VOLUME_ERL_4G     = nvl(it4g.TRAFFIC_VOLUME_ERL, 0)
       where  a.sto_id   = it5g.sto_id
         and  a.as_start = it5g.ZEIT
       ;
       v_count     := v_count     + sql%Rowcount;
       v_count_all := v_count_all + sql%Rowcount;
       --commit alle x Datensätze
        if mod(v_count, 100000) = 0 
          THEN
            insert into OSS_LOG values (0, 'Debug', 'Traffic 5G: update: ' || v_count ,sysdate,null);
            commit;
       
            IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_Traffic_2345G') 
              THEN
                insert into OSS_LOG values (0, 'Info', 'Traffic 5G: exit wegen Zeit' ,sysdate,null);
                RETURN;
            END IF;
            
        end if;
      end loop it5g;
      insert into OSS_LOG values (0, 'Info', 'Traffic 5G: Ende ' || v_count ,sysdate,null);
      commit;
      
      --Update ALL_ Attribute
      --Schleife bis 0 einbauen, um Langläufer zu beachten
      LOOP
        UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA a
        set    a.ALL_TRAFFIC_MB = nvl(A.TRAFFIC_VOLUME_DATA_MB_2G,0) + nvl(A.TRAFFIC_VOLUME_DATA_MB_3G,0) 
                                + nvl(A.TRAFFIC_VOLUME_DATA_MB_4G,0) + nvl(A.TRAFFIC_VOLUME_DATA_MB_5G,0)
        where  ((a.ALL_TRAFFIC_MB is null) OR
        --new condtion beause of TLND-1781 for not valid ALL_TRAFFIC_MB
               (nvl(a.ALL_TRAFFIC_MB,0) != nvl(A.TRAFFIC_VOLUME_DATA_MB_2G,0) + nvl(A.TRAFFIC_VOLUME_DATA_MB_3G,0) 
                                         + nvl(A.TRAFFIC_VOLUME_DATA_MB_4G,0) + nvl(A.TRAFFIC_VOLUME_DATA_MB_5G,0)))
        and    rownum < 500000
        --Zeiteinschränkung
        and    A.AS_START between dvon AND dbis;
        
        v_c         := sql%Rowcount;
        v_count     := v_count + v_c ;
        v_count_all := v_count_all + v_c;
        insert into OSS_LOG values (0, 'Info', 'Update_Traffic_2345G: ALL_TRAFFIC_MB ' || v_count || ' v_c: ' || v_c,sysdate,null);
        commit;
       
        IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_Traffic_2345G') 
          THEN
            insert into OSS_LOG values (0, 'Info', 'Traffic 5G: exit wegen Zeit' ,sysdate,null);
            RETURN;
        END IF;

        EXIT WHEN v_c < 1;
      END LOOP;
              
      insert into OSS_LOG values (0, 'Info', 'Update_Traffic_2345G: Ende  (' || dvon || '-' || dbis || ') ' || v_count_all ,sysdate,null);
      commit;  
   END Update_Traffic_2345G;
   
   
       
   PROCEDURE Update_Traffic_FB (dvon date, dbis date) IS
   --Übernimmt aus TUX Traffic + EQT Zellen den Traffic je nach Layer in leere Attribute
     v_count     number := 0;
     v_count_all number := 0;
     t_ref_FB varchar(50);
     d_stop_date date;
     cursor c_FB_U (vFB number) is 
        select fb_U.*
        from (select fb.STO_ID, fb.ZEIT traffic_date
              ,      count(fb.CELL)                 count_cell
              ,      sum(fb.AVERAGE_ACTIVE_USERS)   AVERAGE_ACTIVE_USERS
              ,      sum(fb.TRAFFIC_VOLUME_DATA_MB) TRAFFIC_VOLUME_DATA_MB
              ,      sum(fb.TRAFFIC_VOLUME_ERL )    TRAFFIC_VOLUME_ERL
              from  (select T2G.CELL, CELL2G.FREQ_BAND,  T2G.ZEIT, CELL2G.LOCATION_BASEKEY STO_ID, NULL AVERAGE_ACTIVE_USERS
                     ,      nvl(T2G.TRAFFIC_VOLUME_DL_DATA_MB_2G,0) + nvl(T2G.TRAFFIC_VOLUME_UL_DATA_MB_2G,0) TRAFFIC_VOLUME_DATA_MB, T2G.TCH_TRAFFIC_ERL_2G TRAFFIC_VOLUME_ERL
                     from   ER_REPORTING.TUX_2G_CELL_VOL T2G
                            JOIN (select *
                                  from    ER_REPORTING.EQT_V_IF_74_OI_2 ec2
                                  where   (length(ec2.LAC) > 0) AND (length(ec2.SAC) IS NULL)  AND (length(ec2.RAC) > 0) 
                                  ) cell2G ON T2G.CELL = CELL2G.CELL_BASEKEY
                     union
                     select T3G.FDDCELL CELL , CELL3G.FREQ_BAND,  T3G.ZEIT ZEIT, CELL3G.LOCATION_BASEKEY STO_ID, NULL AVERAGE_ACTIVE_USERS
                     ,      nvl(T3G.HSDPA_TRAFFIC_MB_3G,0) + nvl(T3G.HSUPA_TRAFFIC_MB_3G,0) + nvl(T3G.REL99_PS_DL_DCH_TRAFFIC_MB_3G ,0) + nvl(T3G.REL99_PS_UL_DCH_TRAFFIC_MB_3G ,0)   TRAFFIC_VOLUME_DATA_MB
                     ,      T3G.VOICE_TRAFFIC_FDDCELL_ERL_3G TRAFFIC_VOLUME_ERL
                     from   ER_REPORTING.TUX_3G_CELL_VOL T3G
                            JOIN (select *
                                  from    ER_REPORTING.EQT_V_IF_74_OI_2 ec3
                                  where   (length(ec3.LAC) > 0) AND (length(ec3.RAC) > 0)  AND (length(ec3.SAC) > 0) 
                                 ) cell3G ON T3G.FDDCELL = CELL3G.CELL_BASEKEY 
                     union
                     select T4G.ECELL CELL , CELL4G.FREQ_BAND, T4G.ZEIT ZEIT, CELL4G.LOCATION_BASEKEY STO_ID, T4G.AVERAGE_ACTIVE_USERS_4G AVERAGE_ACTIVE_USERS
                     ,      nvl(T4G.TRAFFIC_VOLUME_DL_DATA_MB_4G,0) + nvl(T4G.TRAFFIC_VOLUME_UL_DATA_MB_4G,0) TRAFFIC_VOLUME_DATA_MB, T4G.TRAFFIC_VOLUME_VOICE_ERL_4G TRAFFIC_VOLUME_ERL
                     from   ER_REPORTING.TUX_4G_CELL_VOL T4G
                            JOIN (select *
                                  from    ER_REPORTING.EQT_V_IF_74_OI_2 ec4
                                  where   (length(ec4.ECI) > 0)
                                 ) cell4G ON T4G.ECELL = CELL4G.CELL_BASEKEY 
                     union
                     select T5G.NRCELL CELL , CELL5G.FREQ_BAND, T5G.ZEIT, CELL5G.LOCATION_BASEKEY STO_ID, T5G.AVERAGE_ACTIVE_USERS_5G AVERAGE_ACTIVE_USERS
                     ,      nvl(T5G.TRAFFIC_VOLUME_DL_DATA_MB_5G, 0 ) + nvl(T5G.TRAFFIC_VOLUME_UL_DATA_MB_5G,0) TRAFFIC_VOLUME_DATA_MB, 0 TRAFFIC_VOLUME_ERL
                     from   ER_REPORTING.TUX_5G_CELL_VOL T5G
                            JOIN (select *
                                  from    ER_REPORTING.EQT_V_IF_74_OI_2 ec5
                                  where   (length(ec5.NCI) > 0) 
                                 ) cell5G ON T5G.NRCELL = CELL5G.CELL_BASEKEY 
                     ) fb
              where  fb.FREQ_BAND = vFB
              --Zeiteinschränkung
              and    fb.ZEIT between dvon AND dbis 
              group by STO_ID , ZEIT
              ) fb_U
         ,  ER_REPORTING.ER_SM_DAILY_SO_DATA er_target   
         where  er_target.sto_id   = fb_U.sto_id
         and    er_target.as_start = fb_U.traffic_date
         and    ((vFB =  700 and  FB700_CELL is NULL) OR
                 (vFB =  800 and  FB800_CELL is NULL) OR
                 (vFB =  900 and  FB900_CELL is NULL) OR
                 (vFB = 1800 and FB1800_CELL is NULL) OR
                 (vFB = 2100 and FB2100_CELL is NULL) OR
                 (vFB = 2600 and FB2600_CELL is NULL) OR
                 (vFB = 3500 and FB3500_CELL is NULL) 
                )
         and    fb_U.count_cell is not null
         --Zeiteinschränkung
         and    ER_TARGET.AS_START between dvon AND dbis
         and    rownum <= 5000000
         --and    er_target.sto_id in (select site_id from ER_REPORTING.OSS_EXAMPLES )
         --and    er_target.sto_id = '570991914'
         --order by er_target.sto_id, traffic_date desc
         ;
         
      --definition of the used frequency band   
      cursor c_ref_fb is
        select distinct OIFB.FREQ_BAND
        from   ER_REPORTING.EQT_V_IF_74_OI_2 oifb
        where  OIFB.FREQ_BAND is not null
        order by 1
        ;   
   BEGIN
      insert into OSS_LOG values (0, 'Performance', 'Traffic FB_U: Start (' || dvon || '-' || dbis || ')',sysdate,null);
      
      --d_stop_date
      select a.stop_date into d_stop_date 
      from   er_reporting.oss_ende a
      where  a.PROCEDURE = 'Update_Traffic_FB';
      IF d_stop_date < sysdate 
        THEN
          insert into OSS_LOG values (0, 'Info', 'Traffic FB: exit wegen Zeit: ' || to_char(d_stop_date,'DD.MM.YYYY HH24:MI:SS') ,sysdate,null);
          RETURN;
      END IF;
      
      commit;
      
      --die einzelnen FB durchlaufen
      for iref_FB in c_ref_fb loop
          --die Zellen je nach FB durchlaufen
          insert into OSS_LOG values (0, 'Info', 'Traffic FB: '|| iref_FB.FREQ_BAND ,sysdate,null);
          for iFB_U in c_FB_U(iref_FB.FREQ_BAND ) loop
           --nach Frequenz Band unterscheiden
           case iref_FB.FREQ_BAND 
            when 700 then
               update ER_REPORTING.ER_SM_DAILY_SO_DATA a
               set    FB700_CELL                   = nvl(iFB_U.count_cell, 0)
               ,      FB700_AVERAGE_ACTIVE_USERS   = nvl(iFB_U.AVERAGE_ACTIVE_USERS, 0)
               ,      FB700_TRAFFIC_VOLUME_DATA_MB = nvl(iFB_U.TRAFFIC_VOLUME_DATA_MB, 0)
               ,      FB700_TRAFFIC_VOLUME_ERL     = nvl(iFB_U.TRAFFIC_VOLUME_ERL, 0)
               where  a.sto_id   = iFB_U.sto_id
                 and  a.as_start = iFB_U.traffic_date           ;
            when 800 then
               update ER_REPORTING.ER_SM_DAILY_SO_DATA a
               set    FB800_CELL                   = nvl(iFB_U.count_cell, 0)
               ,      FB800_AVERAGE_ACTIVE_USERS   = nvl(iFB_U.AVERAGE_ACTIVE_USERS, 0)
               ,      FB800_TRAFFIC_VOLUME_DATA_MB = nvl(iFB_U.TRAFFIC_VOLUME_DATA_MB, 0)
               ,      FB800_TRAFFIC_VOLUME_ERL     = nvl(iFB_U.TRAFFIC_VOLUME_ERL, 0)
               where  a.sto_id   = iFB_U.sto_id
                 and  a.as_start = iFB_U.traffic_date           ;
            when 900 then
               update ER_REPORTING.ER_SM_DAILY_SO_DATA a
               set    FB900_CELL                   = nvl(iFB_U.count_cell, 0)
               ,      FB900_AVERAGE_ACTIVE_USERS   = nvl(iFB_U.AVERAGE_ACTIVE_USERS, 0)
               ,      FB900_TRAFFIC_VOLUME_DATA_MB = nvl(iFB_U.TRAFFIC_VOLUME_DATA_MB, 0)
               ,      FB900_TRAFFIC_VOLUME_ERL     = nvl(iFB_U.TRAFFIC_VOLUME_ERL, 0)
               where  a.sto_id   = iFB_U.sto_id
                 and  a.as_start = iFB_U.traffic_date           ;
            when 1800 then
               update ER_REPORTING.ER_SM_DAILY_SO_DATA a
               set    FB1800_CELL                   = nvl(iFB_U.count_cell, 0)
               ,      FB1800_AVERAGE_ACTIVE_USERS   = nvl(iFB_U.AVERAGE_ACTIVE_USERS, 0)
               ,      FB1800_TRAFFIC_VOLUME_DATA_MB = nvl(iFB_U.TRAFFIC_VOLUME_DATA_MB, 0)
               ,      FB1800_TRAFFIC_VOLUME_ERL     = nvl(iFB_U.TRAFFIC_VOLUME_ERL, 0)
               where  a.sto_id   = iFB_U.sto_id
                 and  a.as_start = iFB_U.traffic_date           ;
            when 2100 then
               update ER_REPORTING.ER_SM_DAILY_SO_DATA a
               set    FB2100_CELL                   = nvl(iFB_U.count_cell, 0)
               ,      FB2100_AVERAGE_ACTIVE_USERS   = nvl(iFB_U.AVERAGE_ACTIVE_USERS, 0)
               ,      FB2100_TRAFFIC_VOLUME_DATA_MB = nvl(iFB_U.TRAFFIC_VOLUME_DATA_MB, 0)
               ,      FB2100_TRAFFIC_VOLUME_ERL     = nvl(iFB_U.TRAFFIC_VOLUME_ERL, 0)
               where  a.sto_id   = iFB_U.sto_id
                 and  a.as_start = iFB_U.traffic_date           ;
            when 2600 then
               update ER_REPORTING.ER_SM_DAILY_SO_DATA a
               set    FB2600_CELL                   = nvl(iFB_U.count_cell, 0)
               ,      FB2600_AVERAGE_ACTIVE_USERS   = nvl(iFB_U.AVERAGE_ACTIVE_USERS, 0)
               ,      FB2600_TRAFFIC_VOLUME_DATA_MB = nvl(iFB_U.TRAFFIC_VOLUME_DATA_MB, 0)
               ,      FB2600_TRAFFIC_VOLUME_ERL     = nvl(iFB_U.TRAFFIC_VOLUME_ERL, 0)
               where  a.sto_id   = iFB_U.sto_id
                 and  a.as_start = iFB_U.traffic_date           ;
            when 3500 then
               update ER_REPORTING.ER_SM_DAILY_SO_DATA a
               set    FB3500_CELL                   = nvl(iFB_U.count_cell, 0)
               ,      FB3500_AVERAGE_ACTIVE_USERS   = nvl(iFB_U.AVERAGE_ACTIVE_USERS, 0)
               ,      FB3500_TRAFFIC_VOLUME_DATA_MB = nvl(iFB_U.TRAFFIC_VOLUME_DATA_MB, 0)
               ,      FB3500_TRAFFIC_VOLUME_ERL     = nvl(iFB_U.TRAFFIC_VOLUME_ERL, 0)
               where  a.sto_id   = iFB_U.sto_id
                 and  a.as_start = iFB_U.traffic_date           ;
             else
               NULL;
           end case;     
           
           v_count     := v_count     + sql%Rowcount;
           v_count_all := v_count_all + sql%Rowcount;
           
           --commit alle x Datensätze
            if mod(v_count, 500000) = 0 
              THEN
                insert into OSS_LOG values (0, 'Debug', 'Traffic FB'|| iref_FB.FREQ_BAND ||': update: ' || v_count ,sysdate,null);
                commit;
                
                --d_stop_date
                select a.stop_date into d_stop_date 
                from   er_reporting.oss_ende a
                where  a.PROCEDURE = 'Update_Traffic_FB';
                IF d_stop_date < sysdate 
                  THEN
                    insert into OSS_LOG values (0, 'Info', 'Traffic FB'|| iref_FB.FREQ_BAND ||': exit wegen Zeit: ' || to_char(d_stop_date,'DD.MM.YYYY HH24:MI:SS') ,sysdate,null);
                    EXIT;
                END IF;
            end if;
            
          end loop iFB_U;
          
        --d_stop_date
        select a.stop_date into d_stop_date 
        from   er_reporting.oss_ende a
        where  a.PROCEDURE = 'Update_Traffic_FB';
        IF d_stop_date < sysdate 
          THEN
            insert into OSS_LOG values (0, 'Info', 'Traffic FB: exit wegen Zeit: ' || to_char(d_stop_date,'DD.MM.YYYY HH24:MI:SS') ,sysdate,null);
            EXIT;
        END IF;

        insert into OSS_LOG values (0, 'Info', 'Traffic FB'|| iref_FB.FREQ_BAND ||': update: ' || v_count ,sysdate,null);
        commit;
        v_count := 0;
        
      end loop c_ref_fb;
       
      insert into OSS_LOG values (0, 'Info', 'Traffic FB_U: Ende (' || dvon || '-' || dbis || ') ' || v_count_all ,sysdate,null);
      commit;
        
   END Update_Traffic_FB;
   
   PROCEDURE Update_ER_SM_DAILY_SO_DATA IS
   --checkt auf Änderungen des gewünschten Tages
   v_count number := 0;
   d_stop_date date;
   
   BEGIN
     
     insert into OSS_LOG values (0, 'Performance', 'Update_ER_SM_DAILY_SO_DATA: Start',sysdate, null);
     commit;
     
     --d_stop_date
     select a.stop_date into d_stop_date 
     from   er_reporting.oss_ende a
     where  a.PROCEDURE = 'Update_ER_SM_DAILY_SO_DATA';
     IF d_stop_date < sysdate 
       THEN
         insert into OSS_LOG values (0, 'Info', 'Update_ER_SM_DAILY_SO_DATA: exit wegen Zeit: ' || to_char(d_stop_date,'DD.MM.YYYY HH24:MI:SS') ,sysdate,null);
         RETURN;
     END IF;
     
     --INSERT passiert schon im INSERT
     --Ermitteln der Treffermenge pro Tag
     select count(*) into v_count
     from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY tux
     ,      ER_REPORTING.ER_SM_DAILY_SO_DATA er_target
     where  TUX.ZAEHLER_ID = ER_TARGET.ZAEHLER_ID
     and    TUX.AS_START   = ER_TARGET.AS_START
     and    TUX.STO_ID     = ER_TARGET.STO_ID
     --and    TUX.LASTUPDATE <> ER_TARGET.LASTUPDATE
     --and    to_date(TUX.AS_START, 'DD.MM.YY') = to_date('14.08.2021','DD.MM.YY')
     --and    TUX.STO_ID     = '579990969'
     and    ((TUX.VERBRAUCH_HT_KWH <> ER_TARGET.VERBRAUCH_HT_KWH ) or 
             (TUX.VERBRAUCH_NT_KWH <> ER_TARGET.VERBRAUCH_NT_KWH ))
     ;

     insert into OSS_LOG values (0, 'Info', 'Update_ER_SM_DAILY_SO_DATA: count of all updates: ' || v_count ,sysdate, null);

     --select *
     --from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY tux_sm
     --where  TUX_SM. --lastupdate ist noch nciht da
     --Insert der Änderungen
     --Update der Änderungen
     update ER_REPORTING.ER_SM_DAILY_SO_DATA t1
     set    (t1.QUELLE, T1.LASTUPDATE, T1.X_KOORD, T1.Y_KOORD, T1.TYP, T1.SERIALNUMBER, T1.FULLSERIALNUMBER, T1.PHASE1POWER, T1.PHASE2POWER, T1.PHASE3POWER
            ,T1.POWER, T1.ENERGYOUT, T1.ENERGY1_HT, T1.ENERGY2_NT, T1.ENERGY, T1.PHASE1VOLTAGE, T1.PHASE2VOLTAGE, T1.PHASE3VOLTAGE
            ,T1.VERBRAUCH_HT_KWH, T1.VERBRAUCH_NT_KWH, T1.INTERVAL_H, T1.BEREICH, T1.EINBAU_DATUM, T1.ABBAU_DATUM, T1.IST_AKTUELLER_STROMZAEHLER
            ,T1.VERBRAUCH_HT_NT
            ,T1.INSERT_DATE 
            ) = 
            (select t2.QUELLE, T2.LASTUPDATE, T2.X_KOORD, T2.Y_KOORD, T2.TYP, T2.SERIALNUMBER, T2.FULLSERIALNUMBER, T2.PHASE1POWER, T2.PHASE2POWER, T2.PHASE3POWER
            ,T2.POWER, T2.ENERGYOUT, T2.ENERGY1_HT, T2.ENERGY2_NT, T2.ENERGY, T2.PHASE1VOLTAGE, T2.PHASE2VOLTAGE, T2.PHASE3VOLTAGE
            ,T2.VERBRAUCH_HT_KWH, T2.VERBRAUCH_NT_KWH, T2.INTERVAL_H, T2.BEREICH, T2.EINBAU_DATUM, T2.ABBAU_DATUM, T2.IST_AKTUELLER_STROMZAEHLER
            ,nvl(T2.VERBRAUCH_HT_KWH,0) + nvl(T2.VERBRAUCH_NT_KWH, 0)
            ,T2.INSERT_DATE
                                              from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY t2
                                              where  t1.ZAEHLER_ID = t2.ZAEHLER_ID
                                              and    t1.AS_START   = t2.AS_START
                                              and    t1.STO_ID     = t2.STO_ID
                                             ) 
     where  (t1.ZAEHLER_ID, t1.AS_START, t1.STO_ID) in 
                    (select TUX.ZAEHLER_ID, TUX.AS_START, TUX.STO_ID
                     from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY tux
                     ,      ER_REPORTING.ER_SM_DAILY_SO_DATA er_target
                     where  TUX.ZAEHLER_ID  = ER_TARGET.ZAEHLER_ID
                     and    TUX.AS_START    = ER_TARGET.AS_START
                     and    TUX.STO_ID      = ER_TARGET.STO_ID
                     and    TUX.STO_ID is not null
                     --and    TUX.LASTUPDATE <> ER_TARGET.LASTUPDATE
                     --and    to_date(TUX.AS_START, 'DD.MM.YY') = to_date('14.08.2021','DD.MM.YY')
                     --and    TUX.STO_ID     = '579990969'
                     and    ((TUX.VERBRAUCH_HT_KWH <> ER_TARGET.VERBRAUCH_HT_KWH ) OR 
                             (TUX.VERBRAUCH_NT_KWH <> ER_TARGET.VERBRAUCH_NT_KWH ) OR
                             --TLND-1781 Check sum
                             (nvl(ER_TARGET.VERBRAUCH_HT_KWH,0) + nvl(ER_TARGET.VERBRAUCH_NT_KWH,0) != nvl(ER_TARGET.VERBRAUCH_HT_NT,0)))
                     --26.10.2022 Thomas Rochlitz: TLND-2315 ER many Temp-Segment problems, recheck the source code
                     --1000000 -> 500000
                     and    rownum <= 500000
                     --order by TUX.AS_START
                    ) 
     ;


     --##Ändert sich der STO?
     --set NULL for traffic, ...
      
     --Log Ausgabe
     v_count:= sql%Rowcount;
     insert into OSS_LOG values (0, 'Info', 'Update_ER_SM_DAILY_SO_DATA: count of updates: ' || v_count ,sysdate, null);

     
     insert into OSS_LOG values (0, 'Info', 'Update_ER_SM_DAILY_SO_DATA: Ende',sysdate, null);
     Commit;

   END Update_ER_SM_DAILY_SO_DATA;



   PROCEDURE MAKE_HIS IS
   v_count     number := 0;
   v_count_all number := 0;
   v_i         number := 0;
   
   /*cursor Zieltabelle is
     select *
     from   ER_REPORTING.ER_SM_DAILY_SO_DATA a
     order by A.ID;*/ 
   
   BEGIN
        
    insert into OSS_LOG values (0, 'Info', 'Start MAKE_HIS: ' || to_char(sysdate,'YYYYMMDD'),sysdate, null);
        
    /*EXECUTE IMMEDIATE 'create table ER_REPORTING.XHIS_OSS_CONSUMPTION_RESULT_'     || to_char(sysdate,'YYYYMMDD') || ' as (select * from ER_REPORTING.OSS_CONSUMPTION_RESULT)';
    v_count:= v_count+ sql%Rowcount;
    insert into OSS_LOG values (0, 'Info', 'Result XHIS_OSS_CONSUMPTION_RESULT count: ' || v_count,sysdate, null);
    commit;*/

    /*EXECUTE IMMEDIATE 'create table ER_REPORTING.XHIS_ER_SM_DAILY_SO_DATA_'     || to_char(sysdate,'YYYYMMDD') || ' as (select * from ER_REPORTING.ER_SM_DAILY_SO_DATA)';
    v_count:= v_count+ sql%Rowcount;
    insert into OSS_LOG values (0, 'Info', 'Result XHIS_ER_SM_DAILY_SO_DATA count: ' || v_count,sysdate, null);
    commit;*/
    
     /*create table ER_REPORTING.XHIS_ER_SM_DAILY_SO_DATA_20220222 as
     select *
     from   ER_REPORTING.ER_SM_DAILY_SO_DATA
     where  rownum < 1
     ;*/
     
     --v_count := 0;
     /*for i_Zieltabelle in Zieltabelle loop
       insert into ER_REPORTING.XHIS_ER_SM_DAILY_SO_DATA_20220222
       select *
       from   ER_REPORTING.ER_SM_DAILY_SO_DATA b
       where  B.ID = i_Zieltabelle.ID
       ;

       v_count     := v_count     + sql%Rowcount;
       v_count_all := v_count_all + sql%Rowcount;
           
       --commit alle x Datensätze
        --if mod(v_count, 10000) = 0 
        if v_count > 10000 
          THEN
            insert into OSS_LOG values (0, 'Debug', 'MAKE_HIS: update: ' || v_count || ' ' || v_count_all,sysdate,null);
            commit;
            v_count := 0;

            IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_Tmp') 
              THEN
                insert into OSS_LOG values (0, 'Info', 'MAKE_HIS: exit wegen Zeit' ,sysdate,null);
                EXIT;
            END IF;
        end if;
             
         
     end loop i_ntr;*/
     
     LOOP
       /*insert into ER_REPORTING.XHIS_ER_SM_DAILY_SO_DATA_20220225
       select *
       from   ER_REPORTING.ER_SM_DAILY_SO_DATA a
       where  A.ID between v_i * 1000000 and (v_i + 1) * 1000000 - 1
       order by A.ID;*/
       v_count     :=               sql%Rowcount;
       v_count_all := v_count_all + sql%Rowcount;
           
       --commit alle x Datensätze
        --if mod(v_count, 10000) = 0 
       insert into OSS_LOG values (0, 'Debug', 'MAKE_HIS: update: ' || v_count || ' ' || v_count_all,sysdate,null);
       commit;

       IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_Tmp') 
         THEN
           insert into OSS_LOG values (0, 'Info', 'MAKE_HIS: exit wegen Zeit' ,sysdate,null);
           EXIT;
       END IF;
       
       EXIT WHEN v_count < 1;
     END LOOP;  
   
     insert into OSS_LOG values (0, 'Debug', 'MAKE_HIS: update: ' || v_count || ' ' || v_count_all ,sysdate,null);
     commit;
    
    
    
    insert into OSS_LOG values (0, 'Info', 'Ende MAKE_HIS: ' || v_count_all ,sysdate, null);
          
   END MAKE_HIS;



   PROCEDURE Insert_ER_SM_DAILY_SO_DATA IS
  -- Insert daily ER_REPORTING.ER_SM_DAILY_SO_DATA
  --Zeiteinschränkungen auf einen Tag oder vergessene/ verschluckte Werte
  --TRO, 04.12.2021 Logik für METER_VALID aufgenommen
   v_c     number     := 0;
   v_count number     := 0;

       BEGIN
        --Log
        insert into OSS_LOG values (0, 'Performance', 'Insert_ER_SM_DAILY_SO_DATA: Start',sysdate, null);
        commit;
        
        LOOP
          IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Insert_ER_SM_DAILY_SO_DATA') 
            THEN
              insert into OSS_LOG values (0, 'Info', 'Insert_ER_SM_DAILY_SO_DATA: exit wegen Zeit' ,sysdate,null);
              exit;
          END IF;
       
            --Insert data
            --create table er_reporting.ER_SM_DAILY_SO_DATA as
            insert into ER_REPORTING.ER_SM_DAILY_SO_DATA 
             (STO_ID, AS_START, ZAEHLER_ID, X_KOORD, Y_KOORD, TYP, SERIALNUMBER, FULLSERIALNUMBER, PHASE1POWER, PHASE2POWER, PHASE3POWER       
              ,POWER, ENERGYOUT, ENERGY1_HT, ENERGY2_NT, ENERGY, PHASE1VOLTAGE, PHASE2VOLTAGE, PHASE3VOLTAGE, VERBRAUCH_HT_KWH, VERBRAUCH_NT_KWH
              ,INTERVAL_H, Bereich, EINBAU_DATUM, ABBAU_DATUM, IST_AKTUELLER_STROMZAEHLER, QUELLE, LASTUPDATE
              ,Verbrauch_HT_NT
              ,meter_valid  
              --SO
              ,aircondition, fan
              --WBST  
              ,WBST
              --PowerResale
              ,FK_OSS_ENERGY_SALE_DIRECTION
              --Radio Equipment
              ,Radio_vendor, Radio_BTS_CABINET, Radio_BTS_CARD
             )
            select TUX_POWER_H.STO_ID       
            ,      TUX_POWER_H.AS_START       
            ,      TUX_POWER_H.ZAEHLER_ID       
            ,      TUX_POWER_H.X_KOORD       
            ,      TUX_POWER_H.Y_KOORD       
            ,      TUX_POWER_H.TYP       
            ,      TUX_POWER_H.SERIALNUMBER       
            ,      TUX_POWER_H.FULLSERIALNUMBER       
            ,      TUX_POWER_H.PHASE1POWER       
            ,      TUX_POWER_H.PHASE2POWER       
            ,      TUX_POWER_H.PHASE3POWER       
            ,      TUX_POWER_H.POWER       
            ,      TUX_POWER_H.ENERGYOUT       
            ,      TUX_POWER_H.ENERGY1_HT       
            ,      TUX_POWER_H.ENERGY2_NT       
            ,      TUX_POWER_H.ENERGY       
            ,      TUX_POWER_H.PHASE1VOLTAGE       
            ,      TUX_POWER_H.PHASE2VOLTAGE       
            ,      TUX_POWER_H.PHASE3VOLTAGE       
            ,      nvl(TUX_POWER_H.VERBRAUCH_HT_KWH, 0)   VERBRAUCH_HT_KWH    
            ,      nvl(TUX_POWER_H.VERBRAUCH_NT_KWH, 0)   VERBRAUCH_NT_KWH  
            ,      TUX_POWER_H.INTERVAL_H       
            --,      TUX_POWER_H.BEREICH
            ,      nvl(core.corebereich,
                       case TUX_POWER_H.BEREICH
                        when 'Mobile grün'      then 'Mobile'
                        when 'Mobile'           then 'Mobile'
                        when 'Mobile mit Fixed' then 'Mobile'
                        when 'Fixed'            then 'Fixed'
                        when 'Fixed mit Mobile' then 'Fixed'
                        when 'Fixed grün'       then 'Fixed'
                        else NULL --'xxMobile'
                       end) Bereich     
            ,      TUX_POWER_H.EINBAU_DATUM       
            ,      TUX_POWER_H.ABBAU_DATUM       
            ,      TUX_POWER_H.IST_AKTUELLER_STROMZAEHLER
            ,      TUX_POWER_H.QUELLE       
            ,      TUX_POWER_H.LASTUPDATE
            --Verbrauch_HT_NT
            ,      nvl(TUX_POWER_H.VERBRAUCH_HT_KWH,0) + nvl(TUX_POWER_H.VERBRAUCH_NT_KWH,0) Verbrauch_HT_NT
            --meter_valid: -1, 0
            ,      -1
            /*,    CASE substr(TUX_POWER_H.SERIALNUMBER,0,1)
                   WHEN '6' THEN  -1
                   ELSE 0
                 END meter_valid*/
            --SO
            ,      nvl(KLIMA.KLIMA, 'nein') aircondition, nvl(KLIMA.LUEFTUNG, 'nein') fan
            --WBST  
            ,      SITE_INFO.WBST
            --PowerResale
            ,nvl(ENERGY_SALE.FK_ENERGY_SALE_DIRECTION,0) FK_ENERGY_SALE_DIRECTION 
            --Radio Equipment
            ,      eqt.Radio_vendor, eqt.Radio_BTS_CABINET, eqt_card.Radio_BTS_CARD
            --#Transport Equipment
            --the tables itself
            from   ER_REPORTING.TUX_SMART_METER_POWER_HOURLY tux_power_h
                   --Klima
                   LEFT JOIN (select K.SO_NUMMER, K.KLIMA, K.LUEFTUNG 
                               from   ER_REPORTING.NETS_WARTUNG_KLIMA_LUEFTUNG k
                               --nur tmp
                               --where  to_date(k.INSERT_DATE, 'DD.MM.YYYY') = to_date((select distinct max( INSERT_DATE) from ER_REPORTING.NETS_WARTUNG_KLIMA_LUEFTUNG), 'DD.MM.YYYY')
                               --and    K.SO_NUMMER = 212991611
                              ) klima ON TUX_POWER_H.STO_ID = klima.SO_NUMMER
                   --Radio_BTS_CABINET von EQT
                   --#neuen Join bauen, BTS_CABINET und Card in einer Abfrage
                   LEFT JOIN (select EQT.SITE_ID
                                  ,  EQT.VENDOR Radio_vendor
                                  ,  listagg(product_code, ',' on overflow truncate )   within group (order by TYPE, PRODUCT_CODE) Radio_BTS_CABINET
                                  from   (
                                          select e.SITE_ID, e.VENDOR, e.PRODUCT_CODE, E.TYPE
                                          from   ER_REPORTING.EQT_ENERGY_REPORTING e
                                          where  E.STATUS in ('INSTALLED','NMS UNAVAILABLE')
                                          and    E.TYPE   = 'BTS_CABINET'
                                           --nur tmp
                                          --and    to_date(E.INSERT_DATE, 'DD.MM.YYYY') = to_date((select distinct max( INSERT_DATE) from ER_REPORTING.EQT_ENERGY_REPORTING), 'DD.MM.YYYY')
                                         ) eqt
                                  group by EQT.SITE_ID, EQT.VENDOR        
                             ) EQT ON  TUX_POWER_H.STO_ID = EQT.SITE_ID
                   --Radio_CARD von EQT
                   LEFT JOIN (select EQT_c.SITE_ID
                                  ,  EQT_c.VENDOR Radio_vendor
                                  ,  listagg(product_code, ',' on overflow truncate )   within group (order by TYPE, PRODUCT_CODE) Radio_BTS_CARD
                                  from   (
                                          select e.SITE_ID, e.VENDOR, e.PRODUCT_CODE, E.TYPE, E.INSERT_DATE
                                          from   ER_REPORTING.EQT_ENERGY_REPORTING e
                                          where  E.STATUS in ('INSTALLED','NMS UNAVAILABLE')
                                          and    E.TYPE   !='BTS_CABINET'
                                          --nur tmp
                                          --and    to_date(E.INSERT_DATE, 'DD.MM.YYYY') = to_date((select distinct max( INSERT_DATE) from ER_REPORTING.EQT_ENERGY_REPORTING), 'DD.MM.YYYY')
                                          ) eqt_c
                                  group by EQT_c.SITE_ID, EQT_c.VENDOR        
                             ) EQT_card ON  TUX_POWER_H.STO_ID = EQT_card.SITE_ID
                   --Standort Infos
                   LEFT JOIN (select *
                              from   ER_REPORTING.NETS_SITE_INFORMATION so 
                              --nur tmp
                              --where  to_date(so.INSERT_DATE, 'DD.MM.YYYY') = to_date((select distinct max( INSERT_DATE) from ER_REPORTING.NETS_SITE_INFORMATION), 'DD.MM.YYYY')
                              ) site_info
                     ON TUX_POWER_H.STO_ID = SITE_INFO.STANDORT_NR
                   LEFT JOIN (
                        select distinct A.STANDORT_NR, A.STROMZAEHLER_ID, A.LFD_NUMMER_AM_STANDORT
                        ,      A.ZAEHLERTYP, A.BEREICH, A.ZN_NORMIERT
                        ,      A.STROMLIEFERANT_SHORT--, A.STROMLIEFERANT_LONG
                        ,      A.ENERGY_RESALE_SHORT--, A.ENERGY_RESALE_LONG
                        ,      (case 
                                 --(1: Einkauf von Strom)
                                 WHEN A.STROMLIEFERANT_SHORT IS NOT NULL AND A.ENERGY_RESALE_SHORT IS NULL 
                                   THEN 1 
                                 --(3: Reine Unterzählung) 3 vor 2: damit nicht falsch mit 2 klassifiziert wird
                                 WHEN A.STROMLIEFERANT_SHORT IN ('E-Plus Mobilfunk GmbH', 
                                                                'Telefonica Germany GmbH ' || chr(38) || ' Co. OHG', 
                                                                'Telefónica Germany GmbH ' || chr(38) || ' Co. OHG', 
                                                                'Telefónica Germany GmbH ' || chr(38) || ' Co. OHGa')
                                      AND
                                      A.ENERGY_RESALE_SHORT IN ('E-Plus Mobilfunk GmbH', 
                                                                'Telefonica Germany GmbH ' || chr(38) || ' Co. OHG', 
                                                                'Telefónica Germany GmbH ' || chr(38) || ' Co. OHG', 
                                                                'Telefónica Germany GmbH ' || chr(38) || ' Co. OHGa')
                                   THEN 3 
                                 --(2: Verkauf von Strom)
                                 WHEN A.ENERGY_RESALE_SHORT IS NOT NULL  
                                      AND A.ENERGY_RESALE_SHORT NOT IN ('E-Plus Mobilfunk GmbH')
                                      AND A.STROMLIEFERANT_SHORT IN    ('E-Plus Mobilfunk GmbH', 
                                                                        'Telefonica Germany GmbH ' || chr(38) || ' Co. OHG', 
                                                                        'Telefónica Germany GmbH ' || chr(38) || ' Co. OHG', 
                                                                        'Telefónica Germany GmbH ' || chr(38) || ' Co. OHGa')  
                                   THEN 2
                                 --0: kein Treffer 
                                 ELSE 0
                               end) FK_ENERGY_SALE_DIRECTION
                        --,      a.*
                        from   ER_REPORTING.NETS_CONSUMPTION_PER_SITE a
                        where  A.AKTUELLER_STATUS           = 'Aktiv'
                        and    A.IST_AKTUELLER_STROMZAEHLER = 'Ja'
                        ) ENERGY_SALE ON TUX_POWER_H.SERIALNUMBER = ENERGY_SALE.ZN_NORMIERT
                   --Core Sites für den Bereich
                   LEFT JOIN (select distinct 'Core' corebereich, standort_nr 
                              from ER_REPORTING.OSS_CORE_SITE
                              where   standort_nr is not null) core
                     ON TUX_POWER_H.STO_ID = CORE.STANDORT_NR
            where  TUX_POWER_H.STO_ID is not null
            --and    TUX_POWER_H.STO_ID in (select site_id from ER_REPORTING.OSS_EXAMPLES )
            and  (TUX_POWER_H.ZAEHLER_ID, TUX_POWER_H.AS_START ) not in (select EH.ZAEHLER_ID, EH.AS_START
                                                                         from   ER_REPORTING.ER_SM_DAILY_SO_DATA eh )
            --26.10.2022 Thomas Rochlitz: TLND-2315 ER many Temp-Segment problems, recheck the source code, split code
            --500000 -> 50000 -> 25000
            and    rownum <= 25000
            --and    rownum <= 200
            --STO/ Zähler <> 6 ausschließen
            --and    substr(TUX_POWER_H.SERIALNUMBER,0,1) = '6'
            --Test STO 
            --and    TUX_POWER_H.STO_ID in ('215990106') --570990371', '210992412') --'455990085'
            --order by TUX_POWER_H.STO_ID, TUX_POWER_H.AS_START 
            order by TUX_POWER_H.AS_START 
            ;
            
            v_c         := sql%Rowcount;
            v_count     := v_count + v_c ;

            insert into OSS_LOG values (0, 'Debug', 'Insert_ER_SM_DAILY_SO_DATA: ' || v_c || ' ' || v_count,sysdate,null);
            commit;
           
            IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Insert_ER_SM_DAILY_SO_DATA') 
              THEN
                insert into OSS_LOG values (0, 'Info', 'Insert_ER_SM_DAILY_SO_DATA: exit wegen Zeit' ,sysdate,null);
                commit;
                RETURN;
            END IF;

            EXIT WHEN v_c < 1;

        END LOOP ;
        
        insert into OSS_LOG values (0, 'Info', 'Insert_ER_SM_DAILY_SO_DATA: Ende  count: ' || v_count,sysdate,null);
        commit;

   END Insert_ER_SM_DAILY_SO_DATA;
   
   PROCEDURE Update_NE_TYP (dvon date, dbis date) IS
     --legt NT Typen, Anzahlen Router usw.
     v_count number     := 0;
     v_count_all number := 0;

     --Cursor für Radio und Transport
     --for the types without reconsilation
     /*CURSOR NE_TYPEN_REST IS
        select distinct STO.STANDORT_NR_ STO_ID
        ,      ne_typ_radio.NE_TYP_RADIO
        ,      NE_TYP_TRANSPORT.NE_TYP_TRANSPORT
        from   ER_REPORTING.NETS_NESO STO
               LEFT JOIN (select ne_typ_r.sto_id
                          ,      listagg(ne_typ_r.NETZELEMENTTYP, ',' ON overflow truncate) within group (order by ne_typ_r.NETZELEMENTTYP ) NE_TYP_RADIO
                          from   (select NESO.STANDORT_NR_ sto_id, NESO.NETZELEMENTTYP
                                  from   ER_REPORTING.NETS_NESO neso
                                  where  NESO.NETZELEMENT_AKTIV_ = 'Ja'
                                  and    NESO.NESO_AKTIV         = 'Ja'
                                  --aus EQT: 30,31,32,33,35
                                  --Wirklich: 34,36,37,39,47
                                  and    NESO.NETZELEMENTTYP in (40,41,42,43,44,45,46,48,49)
                                  --no x690
                                 ) ne_typ_r
                          group by ne_typ_r.sto_id
                         ) ne_typ_radio ON  STO.STANDORT_NR_ = ne_typ_radio.sto_id           
               LEFT JOIN (select ne_typ_t.sto_id
                          ,      listagg(ne_typ_t.NETZELEMENTTYP, ',' ON overflow truncate) within group (order by ne_typ_t.NETZELEMENTTYP ) NE_TYP_TRANSPORT
                          from   (select NESO.STANDORT_NR_ sto_id, NESO.NETZELEMENTTYP
                                  from   ER_REPORTING.NETS_NESO neso
                                  where  NESO.NETZELEMENT_AKTIV_ = 'Ja'
                                  and    NESO.NESO_AKTIV         = 'Ja'
                                  --from xakta: 25 CC,26 Switch, 27 BGTR. 29 WDM, 79 Router
                                  --Wirklich: 50
                                  and    NESO.NETZELEMENTTYP in (20,21,22)
                                  --no x690
                                 ) ne_typ_t
                          group by ne_typ_t.sto_id
                         ) NE_TYP_TRANSPORT ON  STO.STANDORT_NR_ = NE_TYP_TRANSPORT.sto_id           
        ;*/
        
     --NE direct from network, radio
     CURSOR NE_TYPEN_RADIO IS
        select A.LOCATION_BASEKEY STO_ID, substr(A.BS_BASEKEY,4,2) NE_TYP, count(distinct substr(A.BS_BASEKEY,1,10)) CNT_NE_TYP 
        from   ER_REPORTING.EQT_V_IF_74_OI_2 a
        where  A.LOCATION_BASEKEY       in (Select sto_id from ER_REPORTING.ER_SM_DAILY_SO_DATA)
        --weil _2 sehr alte und NULL Werte hat
        and    A.CELL_STATUS            != 'DELETED IN NMS'
        and    substr(A.BS_BASEKEY,4,2) is not null
        --Test sto
        --and    A.LOCATION_BASEKEY = '460990130' --'467991236'
        --no dataset reduction needed
        group by A.LOCATION_BASEKEY, substr(A.BS_BASEKEY,4,2)
        --order by 1
        --union NESO.NETZELEMENTTYP in (40,41,42,43,44,45,46,48,49)
        UNION
        select to_char(NESO.STANDORT_NR_) STO_ID, NESO.NETZELEMENTTYP NE_TYP, count(*) CNT_NE_TYP
        from   ER_REPORTING.NETS_NESO neso
        where  NESO.ST90_IST_DATUM   is not null
        and    neso.ST690_IST_DATUM  is null
        and    NESO.NETZELEMENTTYP in (40,41,42,43,44,45,46,48,49)
        group by NESO.STANDORT_NR_, NESO.NETZELEMENTTYP
     ;       

     --Cursor für xakta Elemente
     cursor NE_TYPEN_TRANSPORT is
        select xrec.STO_ID, xrec.TYP
        ,      count(*) Anzahl
        ,      listagg(xrec.VENDOR || '-' || xrec.PRODUCT_CODE, ',' ON overflow truncate) within group (order by xrec.TYP, xrec.VENDOR, xrec.PRODUCT_CODE ) DEVICE_TYP
        from   (select XR.SITE_ID STO_ID, XR.TYP, XR.VENDOR, XR.PRODUCT_CODE
               from   ER_REPORTING.XAKTA_NE_RECONCILED xr
               where  XR.NETWORK_RECONCILED        = 'yes'
               and    XR.NETWORK_RECONCILED_RESULT = 'OK'
               and    XR.SITE_ID                  is not null
               --and    substr(XR.SITE_ID, REGEXP_INSTR(XR.SITE_ID, '[0-9]{9}', 1, 1 ), 9) > 100000000
              ) xrec
        where xrec.STO_ID in (select ER.STO_ID 
                               from   ER_REPORTING.ER_SM_DAILY_SO_DATA er 
                              )
        group by xrec.STO_ID, xrec.TYP
        --TRO 28.01.2022: order entfernt
        --order by 1
        --union NESO.NETZELEMENTTYP in (20,21,22)
        UNION
        select to_char(NESO.STANDORT_NR_) STO_ID, NESO.NETZELEMENTTYP TYP, count(*) Anzahl, NULL DEVICE_TYP
        from   ER_REPORTING.NETS_NESO neso
        where  NESO.ST90_IST_DATUM   is not null
        and    neso.ST690_IST_DATUM  is null
        and    NESO.NETZELEMENTTYP in (20,21,22)
        group by NESO.STANDORT_NR_, NESO.NETZELEMENTTYP
        ; 
 
         
   BEGIN
     insert into OSS_LOG values (0, 'Performance', 'Update_NE_TYP: Start (' || dvon || '-' || dbis || ')',sysdate,null);
     commit;
     IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_NE_TYP') 
       THEN
         insert into OSS_LOG values (0, 'Info', 'Update_NE_TYP: exit wegen Zeit' ,sysdate,null);
         RETURN;
     END IF;
        
     
     --Update NE Tyen, start with radio
     insert into OSS_LOG values (0, 'Info', 'Update_NE_TYP: Start Radio (' || dvon || '-' || dbis || ')',sysdate,null);
     commit;
     v_count := 0;
     for i_ntr in NE_TYPEN_RADIO loop
       --pro sto Element finden uns ANZ_ und TYP_ füllen
       CASE i_ntr.NE_TYP 
         WHEN '30' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_30 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_30 is null;
         WHEN '31' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_31 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_31 is null;
         WHEN '32' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_32 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_32 is null;
         WHEN '33' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_33 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_33 is null;
         WHEN '35' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_35 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_35 is null;
         WHEN '40' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_40 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_40 is null;
         WHEN '41' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_41 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_41 is null;
         WHEN '42' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_42 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_42 is null;
         WHEN '43' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_43 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_43 is null;
         WHEN '44' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_44 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_44 is null;
         WHEN '45' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_45 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_45 is null;
         WHEN '46' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_46 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_46 is null;
         WHEN '48' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_48 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_48 is null;
         WHEN '49' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA r
           set    r.CNT_49 = i_ntr.CNT_NE_TYP
           where  r.STO_ID = i_ntr.STO_ID
           and    R.AS_START between dvon AND dbis
           and    r.CNT_49 is null;
         ELSE NULL;
       END CASE;

       v_count     := v_count     + sql%Rowcount;
       v_count_all := v_count_all + sql%Rowcount;
           
       --commit alle x Datensätze
        --if mod(v_count, 10000) = 0 
        if v_count > 100000 
          THEN
            insert into OSS_LOG values (0, 'Debug', 'Update_NE_TYP Radio: update: ' || v_count || ' ' || v_count_all,sysdate,null);
            commit;
            v_count := 0;

            IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_NE_TYP') 
              THEN
                insert into OSS_LOG values (0, 'Info', 'Update_NE_TYP: exit wegen Zeit' ,sysdate,null);
                EXIT;
            END IF;
        end if;
             
         
     end loop i_ntr;
     insert into OSS_LOG values (0, 'Debug', 'Update_NE_TYP Radio: update: ' || v_count ,sysdate,null);
     commit;
     
     --Update Transport: Router, ...
     insert into OSS_LOG values (0, 'Info', 'Update_NE_TYP: Start Transport',sysdate,null);
     commit;
     v_count := 0;
     for i_ntt in NE_TYPEN_TRANSPORT loop
       --pro sto Element finden uns ANZ_ und TYP_ füllen
       CASE i_ntt.TYP 
         WHEN 'BGTR' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA t
           set    t.CNT_BGTR = i_ntt.ANZAHL
           ,      t.TYP_BGTR = i_ntt.DEVICE_TYP
           where  T.STO_ID   = i_ntt.STO_ID
           and    t.AS_START between dvon AND dbis
           and    t.CNT_BGTR is null;
         WHEN 'CC' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA t
           set    t.CNT_CC = i_ntt.ANZAHL
           ,      t.TYP_CC = i_ntt.DEVICE_TYP
           where  T.STO_ID  = i_ntt.STO_ID
           and    t.AS_START between dvon AND dbis
           and    t.CNT_CC is null;
         WHEN 'ROUTER' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA t
           set    t.CNT_ROUTER = i_ntt.ANZAHL
           ,      t.TYP_ROUTER = i_ntt.DEVICE_TYP
           where  T.STO_ID     = i_ntt.STO_ID
           and    t.AS_START between dvon AND dbis
           and    t.CNT_ROUTER is null;
         WHEN 'SWITCH' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA t
           set    t.CNT_SWITCH = i_ntt.ANZAHL
           ,      t.TYP_SWITCH = i_ntt.DEVICE_TYP
           where  T.STO_ID     = i_ntt.STO_ID
           and    t.AS_START between dvon AND dbis
           and    t.CNT_SWITCH is null;
         WHEN 'WDM' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA t
           set    t.CNT_WDM = i_ntt.ANZAHL
           ,      t.TYP_WDM = i_ntt.DEVICE_TYP
           where  T.STO_ID  = i_ntt.STO_ID
           and    t.AS_START between dvon AND dbis
           and    t.CNT_WDM is null;
         WHEN '20' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA t
           set    t.CNT_20 = i_ntt.ANZAHL
           where  T.STO_ID  = i_ntt.STO_ID
           and    t.AS_START between dvon AND dbis
           and    t.CNT_20 is null;
         WHEN '21' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA t
           set    t.CNT_21 = i_ntt.ANZAHL
           where  T.STO_ID  = i_ntt.STO_ID
           and    t.AS_START between dvon AND dbis
           and    t.CNT_21 is null;
         WHEN '22' THEN
           UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA t
           set    t.CNT_22 = i_ntt.ANZAHL
           where  T.STO_ID  = i_ntt.STO_ID
           and    t.AS_START between dvon AND dbis
           and    t.CNT_22 is null;
         ELSE NULL;  
       END CASE;

       v_count     := v_count     + sql%Rowcount;
       v_count_all := v_count_all + sql%Rowcount;
           
       --commit alle x Datensätze
        --if mod(v_count, 10000) = 0 
        if v_count > 100000 
          THEN
            insert into OSS_LOG values (0, 'Debug', 'Update_NE_TYP Transport: update: ' || v_count  || ' ' || v_count_all,sysdate,null);
            commit;
            v_count := 0;

            IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_NE_TYP') 
              THEN
                insert into OSS_LOG values (0, 'Info', 'Update_NE_TYP: exit wegen Zeit' ,sysdate,null);
                EXIT;
            END IF;
        end if;
             
         
     end loop i_ntt;
     insert into OSS_LOG values (0, 'Debug', 'Update_NE_TYP Transport: update: ' || v_count ,sysdate,null);
     commit;
 

     --NE Typen: Rest?#
     
          
     insert into OSS_LOG values (0, 'Info', 'Update_NE_TYP: update ' || v_count ,sysdate,null);
     insert into OSS_LOG values (0, 'Info', 'Update_NE_TYP: Ende (' || dvon || '-' || dbis || ') ' || v_count_all ,sysdate,null);
     commit;     
   END Update_NE_TYP;
   

   PROCEDURE Update_MW (dvon date, dbis date) IS
     --ermittelt aus APT Anz und Typ der Links (MW)
     v_count number     := 0;
     v_count_all number := 0;

     CURSOR cMW is
        select apt.sto STO_ID, count(*) CNT_MW
                ,      listagg(apt.VENDOR || '-' || apt.RICHTFUNKGERAETETYP, ',' ON overflow truncate ) within group (order by apt.VENDOR, apt.RICHTFUNKGERAETETYP  ) TYP_MW
                from  ( select APTA.STO_A STO, APTA.LINK_ID, APTA.VENDOR, APTA.RICHTFUNKGERAETETYP
                        from   ER_REPORTING.APT_V_IF_LINKLISTE apta
                        where  APTA.MAIN_STATUS in ('INSTALL','BETRIEB','ORDER_RUECKBAU') --#Axel Lauer
                        and    substr(apta.LINK_ID,4,2) in ('53','55')
                        --Glasfaser 56,60 Dietmar Garthe
                        --and    APTA.ACTION      not in ('delete')
                        --auf neueste Revision einschränken
                        and    APTA.REV = (select max(APTA2.REV) from   ER_REPORTING.APT_V_IF_LINKLISTE apta2
                                           where  APTA2.ACTION      not in ('delete')
                                           and    apta2.link_id     = APTA.LINK_ID)
                        union
                        select APTB.STO_B STO, APTB.LINK_ID, APTB.VENDOR, APTB.RICHTFUNKGERAETETYP
                        from   ER_REPORTING.APT_V_IF_LINKLISTE aptB
                        where  APTB.MAIN_STATUS in ('INSTALL','BETRIEB','ORDER_RUECKBAU') --#Axel Lauer
                        and    substr(aptb.LINK_ID,4,2) in ('53','55')
                        --and    APTB.ACTION      in ('new') --Roland: Nur new,'Upgrade')
                        --auf neueste Revision einschränken
                        and    APTB.REV = (select max(APTB2.REV) from   ER_REPORTING.APT_V_IF_LINKLISTE aptB2
                                           where  APTB2.ACTION      not in ('delete')
                                           and    aptB2.link_id     = APTB.LINK_ID)              
                    ) apt 
                where apt.sto in (select ER.STO_ID 
                                  from   ER_REPORTING.ER_SM_DAILY_SO_DATA er 
                                  where  er.CNT_MW is null)
                and   rownum < 1000000                  
        group by apt.sto
        --where  STO       = '127990324'
        --#upper(A.SONDERAUSSTATTUNG) like '%HEIZU%'
        --upper(A.SONDERAUSSTATTUNG) like '%HEAT%'
        --#seit gestern
        ;
     
   BEGIN
     insert into OSS_LOG values (0, 'Performance', 'Update_MW: Start (' || dvon || '-' || dbis || ')',sysdate,null);
     commit;
     IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_MW') 
       THEN
         insert into OSS_LOG values (0, 'Info', 'Update_MW: exit wegen Zeit' ,sysdate,null);
         RETURN;
     END IF;
        
     
     --Update MW
     for iMW in cMW loop
       --pro sto Element finden uns ANZ_ und TYP_ füllen
       UPDATE ER_REPORTING.ER_SM_DAILY_SO_DATA t
       set    T.CNT_MW = iMW.CNT_MW
       ,      T.TYP_MW = iMW.TYP_MW
--       ,      T.TYP_MW = substr(iMW.TYP_MW,0,3990)
       where  T.STO_ID = iMW.STO_ID
       and    T.CNT_MW is null
       --erst mal alle, danach nur die neuen
       --TLND-1822 MW Start Zeiteinschränkung auf 5 Tage
       and    T.AS_START between dvon AND dbis
       ; 

       v_count     := v_count     + sql%Rowcount;
       v_count_all := v_count_all + sql%Rowcount;
           
       --commit alle x Datensätze
        if mod(v_count, 10000) = 0 
          THEN
            insert into OSS_LOG values (0, 'Debug', 'Update_MW: update: ' || v_count ,sysdate,null);
            commit;
            v_count := 0;
                
            IF ER_REPORTING.OSS_ER_FUNCTION.PROCEDURE_STOP ('Update_MW') 
              THEN
                insert into OSS_LOG values (0, 'Info', 'Update_MW: exit wegen Zeit' ,sysdate,null);
                EXIT;
            END IF;
        end if;
             
         
     end loop iMW;
     
     insert into OSS_LOG values (0, 'Info', 'Update_MW: update: ' || v_count ,sysdate,null);
     insert into OSS_LOG values (0, 'Info', 'Update_MW: Ende (' || dvon || '-' || dbis || ') ' || v_count_all ,sysdate,null);
     commit;     

   END Update_MW;
   
     
   
   
END OSS_ER_FUNCTION;

/
