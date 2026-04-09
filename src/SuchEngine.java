import java.sql.*;

public class SuchEngine {

    public static void personenSuchen(String s) {
        suche("PERSONEN", s,
                "SELECT id, vorname, nachname, alias_name, beruf, rolle, beschreibung FROM Lukas_personen "
                        + "WHERE vorname LIKE ? OR nachname LIKE ? OR alias_name LIKE ? OR beruf LIKE ? OR beschreibung LIKE ? ORDER BY nachname",
                5);
    }
    public static void fluegeSuchen(String s) {
        suche("FLUEGE", s,
                "SELECT f.id, fz.kennzeichen, fz.spitzname, o1.name AS abflug, o2.name AS ankunft, "
                        + "f.abflug_datum, f.pilotname, f.bemerkung "
                        + "FROM Lukas_fluege f "
                        + "LEFT JOIN Lukas_flugzeuge fz ON f.flugzeug_id = fz.id "
                        + "LEFT JOIN Lukas_orte o1 ON f.abflug_ort_id = o1.id "
                        + "LEFT JOIN Lukas_orte o2 ON f.ankunft_ort_id = o2.id "
                        + "WHERE fz.kennzeichen LIKE ? OR fz.spitzname LIKE ? OR o1.name LIKE ? "
                        + "OR o2.name LIKE ? OR f.pilotname LIKE ? OR f.bemerkung LIKE ? "
                        + "OR CAST(f.abflug_datum AS CHAR) LIKE ? ORDER BY f.abflug_datum DESC",
                7);
    }
    public static void passagiereSuchen(String s) {
        suche("PASSAGIERE", s,
                "SELECT p.vorname, p.nachname, f.abflug_datum, o1.name AS von_ort, o2.name AS nach_ort, "
                        + "fz.spitzname AS flugzeug, fp.bemerkung "
                        + "FROM Lukas_flugpassagiere fp "
                        + "JOIN Lukas_personen p ON fp.person_id = p.id "
                        + "JOIN Lukas_fluege f ON fp.flug_id = f.id "
                        + "LEFT JOIN Lukas_flugzeuge fz ON f.flugzeug_id = fz.id "
                        + "LEFT JOIN Lukas_orte o1 ON f.abflug_ort_id = o1.id "
                        + "LEFT JOIN Lukas_orte o2 ON f.ankunft_ort_id = o2.id "
                        + "WHERE p.vorname LIKE ? OR p.nachname LIKE ? OR p.alias_name LIKE ? "
                        + "ORDER BY f.abflug_datum DESC",
                3);
    }
    public static void orteSuchen(String s) {
        suche("ORTE", s,
                "SELECT id, name, typ, adresse, stadt, bundesstaat, land FROM Lukas_orte "
                        + "WHERE name LIKE ? OR adresse LIKE ? OR stadt LIKE ? OR bundesstaat LIKE ? "
                        + "OR land LIKE ? OR beschreibung LIKE ? ORDER BY name",
                6);
    }
    public static void emailsSuchen(String s) {
        suche("EMAILS", s,
                "SELECT e.id, CONCAT(pa.vorname,' ',pa.nachname) AS absender, "
                        + "CONCAT(pe.vorname,' ',pe.nachname) AS empfaenger, "
                        + "e.betreff, e.email_datum, e.quelle "
                        + "FROM Lukas_emails e "
                        + "LEFT JOIN Lukas_personen pa ON e.absender_id = pa.id "
                        + "LEFT JOIN Lukas_personen pe ON e.empfaenger_id = pe.id "
                        + "WHERE e.betreff LIKE ? OR e.inhalt LIKE ? OR e.absender_email LIKE ? "
                        + "OR e.empfaenger_email LIKE ? OR pa.nachname LIKE ? OR pe.nachname LIKE ? "
                        + "ORDER BY e.email_datum DESC",
                6);
    }

    // 6. DOKUMENTE
    public static void dokumenteSuchen(String s) {
        suche("DOKUMENTE", s,
                "SELECT id, titel, dokument_typ, datum, klassifizierung, quelle FROM Lukas_dokumente "
                        + "WHERE titel LIKE ? OR inhalt_auszug LIKE ? OR dateiname LIKE ? OR quelle LIKE ? "
                        + "ORDER BY datum DESC",
                4);
    }
    public static void dokumentPersonenSuchen(String s) {
        suche("DOKUMENT-PERSONEN", s,
                "SELECT d.titel, d.dokument_typ, CONCAT(p.vorname,' ',p.nachname) AS person, "
                        + "p.rolle, dp.kontext "
                        + "FROM Lukas_dokument_personen dp "
                        + "JOIN Lukas_dokumente d ON dp.dokument_id = d.id "
                        + "JOIN Lukas_personen p ON dp.person_id = p.id "
                        + "WHERE p.vorname LIKE ? OR p.nachname LIKE ? OR d.titel LIKE ? OR dp.kontext LIKE ? "
                        + "ORDER BY d.datum DESC",
                4);
    }
    public static void gerichtsverfahrenSuchen(String s) {
        suche("GERICHTSVERFAHREN", s,
                "SELECT id, aktenzeichen, titel, gericht, verfahrenstyp, status, beginn_datum, ende_datum "
                        + "FROM Lukas_gerichtsverfahren "
                        + "WHERE aktenzeichen LIKE ? OR titel LIKE ? OR gericht LIKE ? "
                        + "OR urteil LIKE ? OR bemerkung LIKE ? ORDER BY beginn_datum DESC",
                5);
    }
    public static void kontakteSuchen(String s) {
        suche("KONTAKTE", s,
                "SELECT CONCAT(p.vorname,' ',p.nachname) AS person, "
                        + "k.telefon, k.email, k.adresse, k.notiz, k.quelle "
                        + "FROM Lukas_kontakte k JOIN Lukas_personen p ON k.person_id = p.id "
                        + "WHERE p.vorname LIKE ? OR p.nachname LIKE ? OR k.telefon LIKE ? "
                        + "OR k.email LIKE ? OR k.adresse LIKE ? OR k.notiz LIKE ? "
                        + "ORDER BY p.nachname",
                6);
    }
    public static void flugzeugeSuchen(String s) {
        suche("FLUGZEUGE", s,
                "SELECT fz.id, fz.kennzeichen, fz.flugzeugtyp, fz.spitzname, "
                        + "CONCAT(p.vorname,' ',p.nachname) AS eigentuemer, fz.beschreibung "
                        + "FROM Lukas_flugzeuge fz LEFT JOIN Lukas_personen p ON fz.eigentuemer_id = p.id "
                        + "WHERE fz.kennzeichen LIKE ? OR fz.flugzeugtyp LIKE ? "
                        + "OR fz.spitzname LIKE ? OR fz.beschreibung LIKE ? ORDER BY fz.kennzeichen",
                4);
    }

    // GLOBALE SUCHE
    public static void globaleSuche(String s) {
        System.out.println("   GLOBALE SUCHE: \"" + s + "\"");
        personenSuchen(s);
        fluegeSuchen(s);
        passagiereSuchen(s);
        orteSuchen(s);
        emailsSuchen(s);
        dokumenteSuchen(s);
        dokumentPersonenSuchen(s);
        gerichtsverfahrenSuchen(s);
        kontakteSuchen(s);
        flugzeugeSuchen(s);
    }


    public static void alleAnzeigen(String tabelle) {
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Lukas_" + tabelle)) {
            System.out.println("\nALLE: Lukas_" + tabelle);
        //    TabellenAnzeige.anzeigen(rs);
        } catch (SQLException e) {
            System.out.println("[FEHLER] " + e.getMessage());
        }
    }

    private static void suche(String label, String suchbegriff, String sql, int paramAnzahl) {
        String param = "%" + suchbegriff + "%";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 1; i <= paramAnzahl; i++) ps.setString(i, param);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n" + label + "-SUCHE: \"" + suchbegriff + "\"");
         //   TabellenAnzeige.anzeigen(rs);
        } catch (SQLException e) {
            System.out.println("[FEHLER " + label + "] " + e.getMessage());
        }
    }
}
