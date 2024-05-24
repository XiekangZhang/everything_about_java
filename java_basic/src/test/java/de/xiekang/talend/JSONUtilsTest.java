package de.xiekang.talend;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JSONUtilsTest {

    final static String testJson = "{\n" +
            "    \"xpds_responseHeader\": {\n" +
            "        \"sessionID\": 7316553,\n" +
            "        \"sessionKey\": \"afd7cef3e09d34bc91a2c8f5a4effc815e231efe4290464dde7b7fbb310a088e\",\n" +
            "        \"version\": 0,\n" +
            "        \"timestamp\": \"2024-05-10T11:00:13.196+01:00\",\n" +
            "        \"errorType\": \"\",\n" +
            "        \"errorMessage\": \"\",\n" +
            "        \"errorMessageId\": \"\",\n" +
            "        \"ignoreWarning\": \"\",\n" +
            "        \"listHeader\": {\n" +
            "            \"lastRow\": \"1\",\n" +
            "            \"offset\": 0\n" +
            "        },\n" +
            "        \"meta\": \"\"\n" +
            "    },\n" +
            "    \"xpds_responseBody\": [\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"6606\",\n" +
            "            \"ableseDatum\": \"2023-10-31\",\n" +
            "            \"ableseKennzeichen\": \"P\",\n" +
            "            \"ablesehinweis\": \"(P) Schätzung (programmseitig)\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-11-07\",\n" +
            "            \"ErstelltZeit\": \"10:04:20+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-11-13\",\n" +
            "            \"AenderungZeit\": \"10:41:31+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-10-31\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 3,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"7613\",\n" +
            "            \"ableseDatum\": \"2023-12-31\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-01-02\",\n" +
            "            \"ErstelltZeit\": \"10:28:50+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-01-02\",\n" +
            "            \"AenderungZeit\": \"10:28:50+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-12-31\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 1,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"8000\",\n" +
            "            \"ableseDatum\": \"2024-02-19\",\n" +
            "            \"ableseKennzeichen\": \"I\",\n" +
            "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-02-19\",\n" +
            "            \"ErstelltZeit\": \"09:12:17+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-02-19\",\n" +
            "            \"AenderungZeit\": \"09:17:20+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-02-19\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 2,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"6727\",\n" +
            "            \"ableseDatum\": \"2023-11-13\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-11-13\",\n" +
            "            \"ErstelltZeit\": \"10:40:54+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-11-13\",\n" +
            "            \"AenderungZeit\": \"10:40:54+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-11-13\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 1,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"8000\",\n" +
            "            \"ableseDatum\": \"2024-02-20\",\n" +
            "            \"ableseKennzeichen\": \"I\",\n" +
            "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-02-20\",\n" +
            "            \"ErstelltZeit\": \"08:32:43+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-02-20\",\n" +
            "            \"AenderungZeit\": \"08:33:07+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-02-20\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 2,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"8000\",\n" +
            "            \"ableseDatum\": \"2024-02-20\",\n" +
            "            \"ableseKennzeichen\": \"I\",\n" +
            "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-02-20\",\n" +
            "            \"ErstelltZeit\": \"08:33:17+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-02-20\",\n" +
            "            \"AenderungZeit\": \"08:34:17+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-02-20\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 2,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"5937\",\n" +
            "            \"ableseDatum\": \"2023-08-08\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"*\",\n" +
            "            \"Vorgang\": \"(20) Startsatz\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-09-11\",\n" +
            "            \"ErstelltZeit\": \"06:48:18+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-11-12\",\n" +
            "            \"AenderungZeit\": \"10:29:15+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-08-08\",\n" +
            "            \"KonvSatzart\": 0,\n" +
            "            \"LaufendeNr\": 3,\n" +
            "            \"Satzart\": 20\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"8000\",\n" +
            "            \"ableseDatum\": \"2024-02-19\",\n" +
            "            \"ableseKennzeichen\": \"I\",\n" +
            "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-02-19\",\n" +
            "            \"ErstelltZeit\": \"11:02:09+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-02-19\",\n" +
            "            \"AenderungZeit\": \"11:25:15+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-02-19\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 2,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"5937\",\n" +
            "            \"ableseDatum\": \"2023-08-08\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"K\",\n" +
            "            \"Vorgang\": \"(20) Startsatz\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-09-11\",\n" +
            "            \"ErstelltZeit\": \"06:48:18+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-09-11\",\n" +
            "            \"AenderungZeit\": \"06:48:18+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-08-08\",\n" +
            "            \"KonvSatzart\": 0,\n" +
            "            \"LaufendeNr\": 3,\n" +
            "            \"Satzart\": 20\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"5937\",\n" +
            "            \"ableseDatum\": \"2023-08-08\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"K\",\n" +
            "            \"Vorgang\": \"(20) Startsatz\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-09-11\",\n" +
            "            \"ErstelltZeit\": \"06:48:18+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-09-13\",\n" +
            "            \"AenderungZeit\": \"01:20:23+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-08-08\",\n" +
            "            \"KonvSatzart\": 0,\n" +
            "            \"LaufendeNr\": 3,\n" +
            "            \"Satzart\": 20\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"9000\",\n" +
            "            \"ableseDatum\": \"2024-04-10\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"1\",\n" +
            "            \"Status\": \"\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-04-23\",\n" +
            "            \"ErstelltZeit\": \"13:20:57+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-04-23\",\n" +
            "            \"AenderungZeit\": \"13:20:57+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-04-10\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 1,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"6533\",\n" +
            "            \"ableseDatum\": \"2023-10-31\",\n" +
            "            \"ableseKennzeichen\": \"P\",\n" +
            "            \"ablesehinweis\": \"(P) Schätzung (programmseitig)\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung(programmseitig)\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-11-07\",\n" +
            "            \"ErstelltZeit\": \"10:04:20+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-11-07\",\n" +
            "            \"AenderungZeit\": \"10:04:20+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-10-31\",\n" +
            "            \"KonvSatzart\": 5,\n" +
            "            \"LaufendeNr\": 1,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"6533\",\n" +
            "            \"ableseDatum\": \"2023-10-31\",\n" +
            "            \"ableseKennzeichen\": \"#\",\n" +
            "            \"ablesehinweis\": \"(#) Schätzung durch Vertrieb\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"*\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-11-07\",\n" +
            "            \"ErstelltZeit\": \"10:04:20+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-11-13\",\n" +
            "            \"AenderungZeit\": \"13:06:42+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-10-31\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 3,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"8827\",\n" +
            "            \"ableseDatum\": \"2024-03-18\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-03-18\",\n" +
            "            \"ErstelltZeit\": \"15:29:15+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-03-18\",\n" +
            "            \"AenderungZeit\": \"15:29:15+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-03-18\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 1,\n" +
            "            \"Satzart\": 27\n" +
            "        }\n" +
            "    ]\n" +
            "}\n";
    final static String test2 = "[\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"6606\",\n" +
            "            \"ableseDatum\": \"2023-10-31\",\n" +
            "            \"ableseKennzeichen\": \"P\",\n" +
            "            \"ablesehinweis\": \"(P) Schätzung (programmseitig)\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-11-07\",\n" +
            "            \"ErstelltZeit\": \"10:04:20+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-11-13\",\n" +
            "            \"AenderungZeit\": \"10:41:31+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-10-31\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 3,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"7613\",\n" +
            "            \"ableseDatum\": \"2023-12-31\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-01-02\",\n" +
            "            \"ErstelltZeit\": \"10:28:50+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-01-02\",\n" +
            "            \"AenderungZeit\": \"10:28:50+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-12-31\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 1,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"8000\",\n" +
            "            \"ableseDatum\": \"2024-02-19\",\n" +
            "            \"ableseKennzeichen\": \"I\",\n" +
            "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-02-19\",\n" +
            "            \"ErstelltZeit\": \"09:12:17+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-02-19\",\n" +
            "            \"AenderungZeit\": \"09:17:20+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-02-19\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 2,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"6727\",\n" +
            "            \"ableseDatum\": \"2023-11-13\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-11-13\",\n" +
            "            \"ErstelltZeit\": \"10:40:54+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-11-13\",\n" +
            "            \"AenderungZeit\": \"10:40:54+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-11-13\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 1,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"8000\",\n" +
            "            \"ableseDatum\": \"2024-02-20\",\n" +
            "            \"ableseKennzeichen\": \"I\",\n" +
            "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-02-20\",\n" +
            "            \"ErstelltZeit\": \"08:32:43+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-02-20\",\n" +
            "            \"AenderungZeit\": \"08:33:07+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-02-20\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 2,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"8000\",\n" +
            "            \"ableseDatum\": \"2024-02-20\",\n" +
            "            \"ableseKennzeichen\": \"I\",\n" +
            "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-02-20\",\n" +
            "            \"ErstelltZeit\": \"08:33:17+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-02-20\",\n" +
            "            \"AenderungZeit\": \"08:34:17+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-02-20\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 2,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"5937\",\n" +
            "            \"ableseDatum\": \"2023-08-08\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"*\",\n" +
            "            \"Vorgang\": \"(20) Startsatz\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-09-11\",\n" +
            "            \"ErstelltZeit\": \"06:48:18+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-11-12\",\n" +
            "            \"AenderungZeit\": \"10:29:15+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-08-08\",\n" +
            "            \"KonvSatzart\": 0,\n" +
            "            \"LaufendeNr\": 3,\n" +
            "            \"Satzart\": 20\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"8000\",\n" +
            "            \"ableseDatum\": \"2024-02-19\",\n" +
            "            \"ableseKennzeichen\": \"I\",\n" +
            "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-02-19\",\n" +
            "            \"ErstelltZeit\": \"11:02:09+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-02-19\",\n" +
            "            \"AenderungZeit\": \"11:25:15+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-02-19\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 2,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"5937\",\n" +
            "            \"ableseDatum\": \"2023-08-08\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"K\",\n" +
            "            \"Vorgang\": \"(20) Startsatz\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-09-11\",\n" +
            "            \"ErstelltZeit\": \"06:48:18+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-09-11\",\n" +
            "            \"AenderungZeit\": \"06:48:18+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-08-08\",\n" +
            "            \"KonvSatzart\": 0,\n" +
            "            \"LaufendeNr\": 3,\n" +
            "            \"Satzart\": 20\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"5937\",\n" +
            "            \"ableseDatum\": \"2023-08-08\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"K\",\n" +
            "            \"Vorgang\": \"(20) Startsatz\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-09-11\",\n" +
            "            \"ErstelltZeit\": \"06:48:18+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-09-13\",\n" +
            "            \"AenderungZeit\": \"01:20:23+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-08-08\",\n" +
            "            \"KonvSatzart\": 0,\n" +
            "            \"LaufendeNr\": 3,\n" +
            "            \"Satzart\": 20\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"9000\",\n" +
            "            \"ableseDatum\": \"2024-04-10\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"1\",\n" +
            "            \"Status\": \"\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-04-23\",\n" +
            "            \"ErstelltZeit\": \"13:20:57+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-04-23\",\n" +
            "            \"AenderungZeit\": \"13:20:57+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-04-10\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 1,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"6533\",\n" +
            "            \"ableseDatum\": \"2023-10-31\",\n" +
            "            \"ableseKennzeichen\": \"P\",\n" +
            "            \"ablesehinweis\": \"(P) Schätzung (programmseitig)\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"S\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung(programmseitig)\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-11-07\",\n" +
            "            \"ErstelltZeit\": \"10:04:20+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-11-07\",\n" +
            "            \"AenderungZeit\": \"10:04:20+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-10-31\",\n" +
            "            \"KonvSatzart\": 5,\n" +
            "            \"LaufendeNr\": 1,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"6533\",\n" +
            "            \"ableseDatum\": \"2023-10-31\",\n" +
            "            \"ableseKennzeichen\": \"#\",\n" +
            "            \"ablesehinweis\": \"(#) Schätzung durch Vertrieb\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"*\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2023-11-07\",\n" +
            "            \"ErstelltZeit\": \"10:04:20+01:00\",\n" +
            "            \"AenderungDatum\": \"2023-11-13\",\n" +
            "            \"AenderungZeit\": \"13:06:42+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2023-10-31\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 3,\n" +
            "            \"Satzart\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"zaehlerStand\": \"8827\",\n" +
            "            \"ableseDatum\": \"2024-03-18\",\n" +
            "            \"ableseKennzeichen\": \"2\",\n" +
            "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
            "            \"setAktive\": \"0\",\n" +
            "            \"Status\": \"\",\n" +
            "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
            "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
            "            \"ErstelltDatum\": \"2024-03-18\",\n" +
            "            \"ErstelltZeit\": \"15:29:15+01:00\",\n" +
            "            \"AenderungDatum\": \"2024-03-18\",\n" +
            "            \"AenderungZeit\": \"15:29:15+01:00\",\n" +
            "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
            "            \"Einheit\": \"\",\n" +
            "            \"Vorgangsdatum\": \"2024-03-18\",\n" +
            "            \"KonvSatzart\": 20,\n" +
            "            \"LaufendeNr\": 1,\n" +
            "            \"Satzart\": 27\n" +
            "        }\n" +
            "    ]\n" +
            "}\n";
    static List<Map<String, Object>> maps = new ArrayList<>();

    @BeforeAll
    static void initialization() {
        maps = JSONUtils.JSONFilter(testJson,
                "$.xpds_responseBody[?(@.setAktive == \"0\" && @.Status == \"\")]");
    }

    @Test
    void getAllSortedJSON() {
        List<String> stringList = JSONUtils.getAllSortedJSON(maps, "ableseDatum");
        System.out.println(stringList);
    }


    @Test
    void getSortedJSON() {
        String jsonstring = JSONUtils.getSortedJSON(maps, "ableseDatum", 0);
        assertEquals("{\"zaehlerStand\":\"8827\",\"ableseDatum\":\"2024-03-18\",\"ableseKennzeichen\":\"2\",\"ablesehinweis\":\"(2) Ablesung durch Kunden\",\"setAktive\":\"0\",\"Status\":\"\",\"Vorgang\":\"(27) Standmitteilung\",\"Anschlussnutzer\":\"Stahl,Sarah\",\"ErstelltDatum\":\"2024-03-18\",\"ErstelltZeit\":\"15:29:15+01:00\",\"AenderungDatum\":\"2024-03-18\",\"AenderungZeit\":\"15:29:15+01:00\",\"LetzteAenderung\":\"2024-04-23T13:20:57.867111+01:00\",\"Einheit\":\"\",\"Vorgangsdatum\":\"2024-03-18\",\"KonvSatzart\":20,\"LaufendeNr\":1,\"Satzart\":27}",
                jsonstring);
    }

    @Test
    void getAllSortedJSONWithConvert() {
        List<Map<String, Object>> mapList = JSONUtils.convertStringtoListOfMap(test2);
        List<String> stringList = JSONUtils.getAllSortedJSON(mapList, "ableseDatum");
        System.out.println(stringList);
    }


    @Test
    void getSortedJSONWithConvert() {
        List<Map<String, Object>> mapList = JSONUtils.convertStringtoListOfMap(test2);
        String jsonstring = JSONUtils.getSortedJSON(mapList, "ableseDatum", 0);
        assertEquals("{\"zaehlerStand\":\"9000\",\"ableseDatum\":\"2024-04-10\",\"ableseKennzeichen\":\"2\",\"ablesehinweis\":\"(2) Ablesung durch Kunden\",\"setAktive\":\"1\",\"Status\":\"\",\"Vorgang\":\"(27) Standmitteilung\",\"Anschlussnutzer\":\"Stahl,Sarah\",\"ErstelltDatum\":\"2024-04-23\",\"ErstelltZeit\":\"13:20:57+01:00\",\"AenderungDatum\":\"2024-04-23\",\"AenderungZeit\":\"13:20:57+01:00\",\"LetzteAenderung\":\"2024-04-23T13:20:57.867111+01:00\",\"Einheit\":\"\",\"Vorgangsdatum\":\"2024-04-10\",\"KonvSatzart\":20,\"LaufendeNr\":1,\"Satzart\":27}",
                jsonstring);
    }

    @Test
    void convertListOfMaptoJSON() {
        List<Map<String, Object>> mapList = JSONUtils.convertStringtoListOfMap(test2);
        String result = JSONUtils.convertListOfMaptoJSON(mapList);
        System.out.println(mapList);
        System.out.println(result);
    }

    @Test
    void getFilterTest() {
        List<Map<String, Object>> result = JSONUtils.JSONFilter(testJson, "$.xpds_responseBody[?(@.Satzart == 27)]");
        System.out.println(result);
        System.out.println(result.get(0).get("LetzteAenderung"));

        String test = "{\n" +
                "    \"kunden\": {\n" +
                "        \"something\": {},\n" +
                "        \"kundenSonstige\": [\n" +
                "            {\n" +
                "                \"attribute\": \"a1\",\n" +
                "                \"wert\": \"12345\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"attribute\": \"a2\",\n" +
                "                \"wert\": \"45678\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        List<Map<String, Object>> result1 = JSONUtils.JSONFilter(test, "$.kunden.kundenSonstige[?(@.attribute == 'a2')]");
        System.out.println(result1.get(0).get("wert"));
        assertEquals("45678", result1.get(0).get("wert"));
    }
}