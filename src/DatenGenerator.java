import java.sql.*;
public class DatenGenerator {

    private static Connection con;

    public static void main(String[] args) {
        try {

            con = DBConnection.getConnection();

            loescheAlteDaten();

            int total = 0;
            total += fuellePersonen();
            total += fuelleOrte();
            total += fuelleFlugzeuge();
            total += fuelleFluege();
            total += fuelleFlugpassagiere();
            total += fuelleEmails();
            total += fuelleDokumente();
            total += fuelleDokumentPersonen();
            total += fuelleGerichtsverfahren();
            total += fuelleKontakte();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loescheAlteDaten() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
        String[] tabellen = {
                "Lukas_flugpassagiere", "Lukas_dokument_personen", "Lukas_kontakte",
                "Lukas_emails", "Lukas_fluege", "Lukas_flugzeuge",
                "Lukas_gerichtsverfahren", "Lukas_dokumente", "Lukas_orte", "Lukas_personen"
        };
        for (String t : tabellen) {
            stmt.execute("DELETE FROM " + t);
            stmt.execute("ALTER TABLE " + t + " AUTO_INCREMENT = 1");
        }
        stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
        stmt.close();
    }
    private static int fuellePersonen() throws SQLException {
        String sql = "INSERT INTO Lukas_personen (vorname, nachname, alias_name, geburtsdatum, nationalitaet, beruf, rolle, beschreibung) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        String[][] daten = {
                {"Jeffrey", "Epstein", "JE", "1953-01-20", "US-amerikanisch", "Financier / Investmentbanker", "Beschuldigter", "Hauptbeschuldigter. Gruender von J. Epstein & Co. Verurteilt 2008 in Florida, erneut angeklagt 2019 in New York. Tod am 10.08.2019 in Haft."},
                {"Ghislaine", "Maxwell", "GM", "1961-12-25", "britisch", "Socialite / Geschaeftsfrau", "Beschuldigter", "Tochter von Robert Maxwell. Partnerin von Epstein. Verurteilt 2021, 20 Jahre Haft."},
                {"Virginia", "Giuffre", "Virginia Roberts", "1983-08-09", "US-amerikanisch", null, "Opfer", "Hauptzeugin. Wurde ab dem Alter von 16 Jahren missbraucht. Klaegerin in mehreren Zivilklagen."},
                {"Sarah", "Ransome", null, "1985-03-14", "suedafrikanisch", null, "Opfer", "Opfer, wurde auf Little Saint James festgehalten. Fluchtversuch durch Schwimmen."},
                {"Courtney", "Wild", null, "1988-07-22", "US-amerikanisch", null, "Opfer", "Opfer aus Palm Beach. Wurde als 14-Jaehrige rekrutiert."},
                {"Annie", "Farmer", null, "1980-11-05", "US-amerikanisch", null, "Opfer", "Opfer. Wurde auf der Zorro Ranch in New Mexico missbraucht."},
                {"Maria", "Hernandez", null, "1986-04-18", "US-amerikanisch", null, "Opfer", "Anonymisiertes Opfer. Sagte vor der Grand Jury aus."},
                {"Jane", "Doe_3", null, "1987-09-30", "US-amerikanisch", null, "Opfer", "Anonymisiertes Opfer Nr. 3 aus den Gerichtsakten."},
                {"Jane", "Doe_4", null, "1989-02-11", "US-amerikanisch", null, "Opfer", "Anonymisiertes Opfer Nr. 4 aus den Gerichtsakten."},
                {"Jane", "Doe_5", null, "1984-06-25", "britisch", null, "Opfer", "Anonymisiertes Opfer Nr. 5, rekrutiert in London."},
                {"Larry", "Visoski", "Captain Larry", "1955-05-10", "US-amerikanisch", "Pilot", "Mitarbeiter", "Chefpilot der Boeing 727 Lolita Express. Flog von 1991 bis 2013 fuer Epstein."},
                {"David", "Rodgers", null, "1960-08-20", "US-amerikanisch", "Pilot", "Mitarbeiter", "Co-Pilot. Fuehrte die Fluglogbuecher. Sagte 2009 unter Eid aus."},
                {"Nadia", "Marcinkova", "Nadia M.", "1984-03-25", "slowakisch", "Pilotin / Model", "Mitarbeiter", "Ehemaliges Opfer, spaeter Pilotin fuer Epstein."},
                {"Jean-Luc", "Brunel", null, "1946-11-18", "franzoesisch", "Modelagent", "Beschuldigter", "Gruender von MC2 Model Agency. Soll junge Models rekrutiert haben. Tod in Haft 2022."},
                {"Lesley", "Groff", null, "1962-04-12", "US-amerikanisch", "Assistentin", "Mitarbeiter", "Persoenliche Assistentin von Epstein. Verwaltete Termine und Reiseplaene."},
                {"Sarah", "Kellen", "Sarah K.", "1979-09-08", "US-amerikanisch", "Assistentin", "Mitarbeiter", "Assistentin. Soll aktiv an der Rekrutierung beteiligt gewesen sein."},
                {"Adriana", "Ross", null, "1978-01-30", "US-amerikanisch", "Assistentin", "Mitarbeiter", "Assistentin und Masseuse. Namentlich in Gerichtsdokumenten erwaehnt."},
                {"Miles", "Alexander", null, "1970-07-14", "US-amerikanisch", "Hausverwalter", "Mitarbeiter", "Hausverwalter des Manhattan-Anwesens."},
                {"Cimberly", "Espinosa", null, "1975-02-28", "US-amerikanisch", "Haushaltshilfe", "Mitarbeiter", "Haushaltshilfe auf Little Saint James. Sagte als Zeugin aus."},
                {"Rinaldo", "Rizzo", null, "1968-11-03", "US-amerikanisch", "Chauffeur", "Mitarbeiter", "Persoenlicher Chauffeur in New York und Palm Beach."},
                {"Brad", "Edwards", null, "1972-06-15", "US-amerikanisch", "Rechtsanwalt", "Zeuge", "Anwalt der Opfer. Vertritt ueber 50 Betroffene."},
                {"Alexander", "Acosta", null, "1969-01-16", "US-amerikanisch", "Staatsanwalt / Politiker", "Zeuge", "Ehemaliger US-Staatsanwalt in Florida. Verantwortlich fuer den umstrittenen Plea Deal 2008."},
                {"Julie", "Brown", null, "1970-10-20", "US-amerikanisch", "Journalistin", "Zeuge", "Investigativjournalistin Miami Herald. Ihre Recherchen fuehrten zur Wiederaufnahme 2019."},
                {"Joseph", "Recarey", "Joe Recarey", "1965-03-08", "US-amerikanisch", "Polizist", "Zeuge", "Leitender Ermittler der Palm Beach Police. Sammelte ueber 30 Zeugenaussagen."},
                {"Mark", "Epstein", null, "1955-09-12", "US-amerikanisch", "Geschaeftsmann", "Kontakt", "Bruder von Jeffrey Epstein. Verwaltete Immobilien in New York."},
                {"Eva", "Dubin", "Eva Andersson", "1961-02-14", "schwedisch", "Aerztin / Ex-Model", "Kontakt", "Ex-Freundin von Epstein."},
                {"Peter", "Listerman", null, "1963-08-22", "russisch", "Agent / Vermittler", "Kontakt", "Sogenannter Socialite-Vermittler. Im schwarzen Buch eingetragen."},
                {"Haley", "Robson", null, "1988-12-05", "US-amerikanisch", null, "Zeuge", "Wurde als Opfer rekrutiert, rekrutierte spaeter selbst andere. Kronzeugin."},
                {"Alfredo", "Rodriguez", null, "1960-04-17", "US-amerikanisch", "Butler", "Mitarbeiter", "Butler im Palm Beach Anwesen. Stahl das schwarze Buch. Verurteilt wegen Justizbehinderung."},
                {"Emmy", "Taylor", "Emmy T.", "1978-07-09", "britisch", "Assistentin", "Mitarbeiter", "Assistentin von Ghislaine Maxwell in London."}
        };

        for (String[] d : daten) {
            for (int i = 0; i < 8; i++) ps.setString(i + 1, d[i]);
            ps.addBatch();
        }
        int count = ps.executeBatch().length;
        ps.close();
        System.out.println("  [OK] Lukas_personen:           " + count + " Eintraege");
        return count;
    }
    private static int fuelleOrte() throws SQLException {
        String sql = "INSERT INTO Lukas_orte (name, typ, adresse, stadt, bundesstaat, land, beschreibung) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        String[][] daten = {
                {"Little Saint James", "Insel", null, null, "US Virgin Islands", "USA", "Privatinsel, 29 Hektar. Spitzname Pedophile Island."},
                {"Great Saint James", "Insel", null, null, "US Virgin Islands", "USA", "Zweite Insel, 2016 gekauft. 125 Hektar."},
                {"Zorro Ranch", "Ranch", "State Road 41", "Stanley", "New Mexico", "USA", "3200 Hektar Ranch suedoestlich von Santa Fe."},
                {"East 71st Street Mansion", "Haus", "9 East 71st Street", "New York City", "New York", "USA", "7-stoeckiges Stadthaus. Wert ca. 77 Mio USD."},
                {"Palm Beach Villa", "Haus", "358 El Brillo Way", "Palm Beach", "Florida", "USA", "Villa wo die meisten Missbrauchsfaelle stattfanden."},
                {"Avenue Foch Wohnung", "Wohnung", "22 Avenue Foch", "Paris", null, "Frankreich", "Luxuswohnung nahe Arc de Triomphe."},
                {"Mayfair Wohnung", "Wohnung", "44 Kinnerton Street", "London", null, "Grossbritannien", "Maxwells Londoner Wohnung."},
                {"Teterboro Airport", "Flughafen", null, "Teterboro", "New Jersey", "USA", "Privatflughafen bei NYC. Hauptstartpunkt."},
                {"Cyril E. King Airport", "Flughafen", null, "St. Thomas", "US Virgin Islands", "USA", "Naechster Flughafen zu Little Saint James."},
                {"Palm Beach International Airport", "Flughafen", null, "West Palm Beach", "Florida", "USA", "Flughafen fuer Fluege von/nach Florida."},
                {"Santa Fe Municipal Airport", "Flughafen", null, "Santa Fe", "New Mexico", "USA", "Naechster Flughafen zur Zorro Ranch."},
                {"Columbus Airport", "Flughafen", null, "Columbus", "Ohio", "USA", "Zwischenstopp auf mehreren Flugrouten."},
                {"Miami Executive Airport", "Flughafen", null, "Miami", "Florida", "USA", "Alternativer Flughafen Suedost-Florida."},
                {"Bedford Airport", "Flughafen", null, "Bedford", "Massachusetts", "USA", "Privatflughafen fuer Nordost-Routen."},
                {"MC2 Model Agency Buero", "Buero", "7221 Fisher Island Drive", "Miami Beach", "Florida", "USA", "Modelagentur von Jean-Luc Brunel."},
                {"Villefontaine Anwesen", "Haus", null, "Villefontaine", null, "Frankreich", "Brunels Anwesen in Frankreich."},
                {"New Albany Haus", "Haus", null, "Columbus", "Ohio", "USA", "Haus in der Naehe von Wexners Anwesen."},
                {"Metropolitan Correctional Center", "Sonstiges", "150 Park Row", "New York City", "New York", "USA", "Bundesgefaengnis. Hier starb Epstein am 10.08.2019."},
                {"MDC Brooklyn", "Sonstiges", "80 29th Street", "Brooklyn", "New York", "USA", "Maxwell war hier inhaftiert."},
                {"FCI Tallahassee", "Sonstiges", null, "Tallahassee", "Florida", "USA", "Aktueller Haftort von Ghislaine Maxwell."}
        };

        for (String[] d : daten) {
            for (int i = 0; i < 7; i++) ps.setString(i + 1, d[i]);
            ps.addBatch();
        }
        int count = ps.executeBatch().length;
        ps.close();
        System.out.println("  [OK] Lukas_orte:               " + count + " Eintraege");
        return count;
    }

    private static int fuelleFlugzeuge() throws SQLException {
        String sql = "INSERT INTO Lukas_flugzeuge (kennzeichen, flugzeugtyp, spitzname, eigentuemer_id, beschreibung) VALUES (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        Object[][] daten = {
                {"N908JE", "Boeing 727-31", "Lolita Express", 1, "Hauptflugzeug von Epstein. Registriert 1999-2013."},
                {"N986JE", "Gulfstream IV SP", null, 1, "Kleinerer Privatjet fuer Kurzstrecken."},
                {"N120JE", "Bell 430 Helicopter", null, 1, "Helikopter fuer Transfer zur Insel."},
                {"N225JE", "Cessna 421", null, 1, "Kleinflugzeug fuer Karibik-Kurzstrecken."},
                {"N176JE", "Gulfstream G550", null, 1, "Ersatz fuer Boeing 727 ab 2010."}
        };

        for (Object[] d : daten) {
            ps.setString(1, (String) d[0]);
            ps.setString(2, (String) d[1]);
            ps.setString(3, (String) d[2]);
            ps.setInt(4, (int) d[3]);
            ps.setString(5, (String) d[4]);
            ps.addBatch();
        }
        int count = ps.executeBatch().length;
        ps.close();
        System.out.println("  [OK] Lukas_flugzeuge:          " + count + " Eintraege");
        return count;
    }
    private static int fuelleFluege() throws SQLException {
        String sql = "INSERT INTO Lukas_fluege (flugzeug_id, abflug_ort_id, ankunft_ort_id, abflug_datum, abflug_zeit, pilotname, bemerkung, quelle) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        Object[][] daten = {
                {1, 8, 9, "1999-06-12", "08:30:00", "Larry Visoski", "Erster Flug nach St. Thomas", "FAA Fluglog"},
                {1, 9, 10, "1999-06-18", "14:00:00", "Larry Visoski", "Rueckflug von Virgin Islands", "FAA Fluglog"},
                {1, 8, 10, "2000-01-22", "09:15:00", "Larry Visoski", "NYC nach Palm Beach", "FAA Fluglog"},
                {1, 10, 9, "2000-01-25", "11:00:00", "Larry Visoski", "Palm Beach nach St. Thomas", "FAA Fluglog"},
                {1, 9, 8, "2000-02-01", "16:30:00", "David Rodgers", "Rueckflug nach Teterboro", "FAA Fluglog"},
                {2, 8, 10, "2000-05-14", "07:45:00", "David Rodgers", "Gulfstream NYC-PBI", "FAA Fluglog"},
                {2, 10, 8, "2000-05-16", "18:00:00", "David Rodgers", "Gulfstream Rueckflug", "FAA Fluglog"},
                {1, 8, 9, "2001-03-10", "08:00:00", "Larry Visoski", "Grosse Gruppe an Bord", "FAA Fluglog"},
                {1, 9, 8, "2001-03-17", "15:30:00", "Larry Visoski", "Rueckflug mit Zwischenstopp", "FAA Fluglog"},
                {1, 10, 11, "2001-07-04", "10:00:00", "Larry Visoski", "Flug zur Zorro Ranch", "FAA Fluglog"},
                {1, 11, 10, "2001-07-08", "12:00:00", "Larry Visoski", "Rueckflug von Santa Fe", "FAA Fluglog"},
                {2, 8, 12, "2001-09-20", "09:00:00", "David Rodgers", "Flug nach Columbus", "FAA Fluglog"},
                {2, 12, 8, "2001-09-22", "17:00:00", "David Rodgers", "Rueckflug von Columbus", "FAA Fluglog"},
                {1, 8, 9, "2002-02-14", "08:00:00", "Larry Visoski", "Valentinstag-Flug", "FAA Fluglog"},
                {1, 9, 10, "2002-02-20", "13:00:00", "Larry Visoski", "Transfer nach Palm Beach", "FAA Fluglog"},
                {1, 10, 8, "2002-03-01", "16:00:00", "Larry Visoski", "Rueckflug nach NYC", "FAA Fluglog"},
                {1, 8, 9, "2002-07-15", "07:30:00", "Larry Visoski", "Sommerreise zur Insel", "FAA Fluglog"},
                {3, 9, 9, "2002-07-15", "11:00:00", "Nadia Marcinkova", "Heli-Transfer zur Insel", "Privatlog"},
                {1, 9, 8, "2002-08-01", "14:00:00", "Larry Visoski", "Ende Sommeraufenthalt", "FAA Fluglog"},
                {2, 8, 13, "2002-11-10", "08:30:00", "David Rodgers", "Flug nach Miami", "FAA Fluglog"},
                {2, 13, 10, "2002-11-12", "10:00:00", "David Rodgers", "Miami nach Palm Beach", "FAA Fluglog"},
                {1, 8, 9, "2003-01-05", "09:00:00", "Larry Visoski", "Neujahrsreise", "FAA Fluglog"},
                {1, 9, 8, "2003-01-12", "15:00:00", "Larry Visoski", "Rueckflug nach NYC", "FAA Fluglog"},
                {1, 10, 9, "2003-04-18", "11:30:00", "Larry Visoski", "Osterferien-Flug", "FAA Fluglog"},
                {1, 9, 10, "2003-04-25", "16:00:00", "Larry Visoski", "Rueckflug Ostern", "FAA Fluglog"},
                {2, 8, 14, "2003-08-05", "07:00:00", "David Rodgers", "Flug nach Bedford", "FAA Fluglog"},
                {2, 14, 8, "2003-08-07", "18:30:00", "David Rodgers", "Rueckflug von Bedford", "FAA Fluglog"},
                {1, 8, 9, "2004-06-20", "08:00:00", "Larry Visoski", "Sommeranfang", "FAA Fluglog"},
                {1, 9, 8, "2004-07-05", "14:30:00", "Larry Visoski", "Rueckflug nach Feiertagen", "FAA Fluglog"},
                {4, 9, 9, "2004-08-10", "10:00:00", "David Rodgers", "Cessna Inselhopping", "Privatlog"},
                {1, 8, 11, "2005-03-15", "09:00:00", "Larry Visoski", "Flug zur Ranch", "FAA Fluglog"},
                {1, 11, 8, "2005-03-20", "13:00:00", "Larry Visoski", "Rueckflug von Ranch", "FAA Fluglog"},
                {2, 10, 9, "2005-06-01", "10:30:00", "David Rodgers", "Palm Beach - St. Thomas", "FAA Fluglog"},
                {2, 9, 10, "2005-06-10", "16:00:00", "David Rodgers", "Rueckflug", "FAA Fluglog"},
                {5, 8, 9, "2010-12-20", "08:00:00", "Larry Visoski", "Erster Flug Gulfstream G550", "FAA Fluglog"},
                {5, 9, 8, "2010-12-28", "15:00:00", "Larry Visoski", "Weihnachtsrueckflug", "FAA Fluglog"},
                {5, 8, 10, "2011-04-10", "09:30:00", "Larry Visoski", "Fruehlingsflug Florida", "FAA Fluglog"},
                {5, 10, 9, "2011-04-15", "11:00:00", "Larry Visoski", "Weiter nach Virgin Islands", "FAA Fluglog"},
                {5, 9, 8, "2011-04-22", "14:00:00", "Larry Visoski", "Rueckflug nach NYC", "FAA Fluglog"},
                {5, 8, 10, "2012-09-05", "08:00:00", "David Rodgers", "Letzter dokumentierter Flug", "FAA Fluglog"}
        };

        for (Object[] d : daten) {
            ps.setInt(1, (int) d[0]);
            ps.setInt(2, (int) d[1]);
            ps.setInt(3, (int) d[2]);
            ps.setString(4, (String) d[3]);
            ps.setString(5, (String) d[4]);
            ps.setString(6, (String) d[5]);
            ps.setString(7, (String) d[6]);
            ps.setString(8, (String) d[7]);
            ps.addBatch();
        }
        int count = ps.executeBatch().length;
        ps.close();
        System.out.println("  [OK] Lukas_fluege:             " + count + " Eintraege");
        return count;
    }
    private static int fuelleFlugpassagiere() throws SQLException {
        String sql = "INSERT INTO Lukas_flugpassagiere (flug_id, person_id, bemerkung) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        Object[][] daten = {
                {1, 1, "Eigentuemer"}, {1, 2, "Begleitung"}, {1, 11, "Pilot"},
                {3, 1, null}, {3, 2, null}, {3, 15, "Assistentin"},
                {4, 1, null}, {4, 2, null}, {4, 3, "Opfer"},
                {5, 1, null}, {5, 2, null}, {5, 15, null},
                {8, 1, "Eigentuemer"}, {8, 2, null}, {8, 14, "Jean-Luc Brunel"},
                {8, 13, "Nadia"}, {8, 5, "Opfer"}, {8, 6, "Opfer"},
                {9, 1, null}, {9, 2, null}, {9, 14, null},
                {10, 1, null}, {10, 2, null}, {10, 6, "Opfer - Annie Farmer"},
                {12, 1, null}, {12, 25, "Mark Epstein"},
                {14, 1, null}, {14, 2, null}, {14, 16, "Sarah Kellen"}, {14, 7, "Opfer"},
                {17, 1, null}, {17, 2, null}, {17, 14, null}, {17, 15, null}, {17, 8, "Opfer"},
                {20, 1, null}, {20, 17, "Adriana Ross"},
                {22, 1, null}, {22, 2, null}, {22, 26, "Eva Dubin"},
                {24, 1, null}, {24, 2, null}, {24, 16, null}, {24, 9, "Opfer"},
                {28, 1, null}, {28, 2, null}, {28, 14, null}, {28, 13, null}, {28, 10, "Opfer London"},
                {31, 1, null}, {31, 15, null}, {31, 16, null},
                {33, 1, null}, {33, 2, null}, {33, 4, "Sarah Ransome"},
                {35, 1, null}, {35, 2, null}, {35, 15, null},
                {37, 1, null}, {37, 20, "Chauffeur Rinaldo"},
                {38, 1, null}, {38, 2, null},
                {39, 1, null}, {39, 15, null},
                {40, 1, null}, {40, 15, "Lesley Groff"}
        };

        for (Object[] d : daten) {
            ps.setInt(1, (int) d[0]);
            ps.setInt(2, (int) d[1]);
            ps.setString(3, (String) d[2]);
            ps.addBatch();
        }
        int count = ps.executeBatch().length;
        ps.close();
        System.out.println("  [OK] Lukas_flugpassagiere:     " + count + " Eintraege");
        return count;
    }
    private static int fuelleEmails() throws SQLException {
        String sql = "INSERT INTO Lukas_emails (absender_id, empfaenger_id, betreff, inhalt, email_datum, absender_email, empfaenger_email, anhang_vorhanden, quelle) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        Object[][] daten = {
                {2, 1, "Reiseplanung Karibik", "Die Maedchen kommen Freitag an. Alles wie besprochen.", "2003-04-10 09:15:00", "gmax@example.com", "jeff@example.com", 0, "Gerichtsakten Maxwell 2021"},
                {1, 2, "Re: Reiseplanung Karibik", "Gut. Larry bereitet den Flug vor.", "2003-04-10 10:30:00", "jeff@example.com", "gmax@example.com", 0, "Gerichtsakten Maxwell 2021"},
                {15, 1, "Terminuebersicht Woche 16", "Montag: Massage 14 Uhr. Dienstag: Abendessen. Mittwoch: Flug PBI 08:00.", "2003-04-13 08:00:00", "lesley@example.com", "jeff@example.com", 1, "Gerichtsakten Maxwell 2021"},
                {16, 2, "Neue Kontakte London", "Habe drei neue Maedchen kennengelernt. Details im Anhang.", "2004-02-20 14:45:00", "sarah.k@example.com", "gmax@example.com", 1, "Gerichtsakten Maxwell 2021"},
                {2, 16, "Re: Neue Kontakte London", "Sehr gut. Bring sie naechste Woche mit.", "2004-02-20 16:00:00", "gmax@example.com", "sarah.k@example.com", 0, "Gerichtsakten Maxwell 2021"},
                {14, 1, "Models fuer Event", "Habe fuenf neue Models von MC2. Alter 16-19.", "2004-05-15 11:30:00", "jlbrunel@example.com", "jeff@example.com", 1, "Gerichtsakten Brunel"},
                {1, 14, "Re: Models fuer Event", "Schick sie zur Insel. Flug am Donnerstag.", "2004-05-15 12:00:00", "jeff@example.com", "jlbrunel@example.com", 0, "Gerichtsakten Brunel"},
                {15, 2, "Schwarzes Buch Update", "Neue Nummern hinzugefuegt. Aktuelle Version im Anhang.", "2004-08-03 09:00:00", "lesley@example.com", "gmax@example.com", 1, "Palm Beach PD"},
                {2, 1, "Problem Palm Beach", "Ein Maedchen hat ihrer Mutter erzaehlt. Wir muessen vorsichtiger sein.", "2005-03-10 22:15:00", "gmax@example.com", "jeff@example.com", 0, "Gerichtsakten 2019 SDNY"},
                {1, 2, "Re: Problem Palm Beach", "Kein Kontakt mehr mit ihr. Lesley kuemmert sich.", "2005-03-11 07:30:00", "jeff@example.com", "gmax@example.com", 0, "Gerichtsakten 2019 SDNY"},
                {16, 15, "Massage-Termine diese Woche", "Dienstag und Donnerstag jeweils 15 Uhr.", "2005-05-02 10:00:00", "sarah.k@example.com", "lesley@example.com", 0, "Gerichtsakten 2019 SDNY"},
                {17, 1, "Massage bestaetigt", "Bin morgen um 14 Uhr da.", "2005-06-14 18:00:00", "adriana@example.com", "jeff@example.com", 0, "Gerichtsakten 2019 SDNY"},
                {2, 14, "Paris naechste Woche", "Komme Dienstag. Hast du die Wohnung vorbereitet?", "2005-09-01 12:00:00", "gmax@example.com", "jlbrunel@example.com", 0, "Gerichtsakten Brunel"},
                {14, 2, "Re: Paris naechste Woche", "Alles bereit. Zwei neue Maedchen eingeladen.", "2005-09-01 14:30:00", "jlbrunel@example.com", "gmax@example.com", 0, "Gerichtsakten Brunel"},
                {15, 1, "Polizei war da", "Heute waren Beamte am Tor. Haben Fragen gestellt.", "2005-10-20 17:30:00", "lesley@example.com", "jeff@example.com", 0, "Palm Beach PD"},
                {1, 15, "Re: Polizei war da", "Sage nichts. Anwalt ist informiert.", "2005-10-20 18:00:00", "jeff@example.com", "lesley@example.com", 0, "Palm Beach PD"},
                {30, 2, "Treffen London", "Emmy hat alles arrangiert. Drei Maedchen kommen Samstag.", "2003-11-05 13:00:00", "emmy.t@example.com", "gmax@example.com", 0, "Gerichtsakten Maxwell 2021"},
                {2, 30, "Re: Treffen London", "Perfekt. Danke Emmy.", "2003-11-05 14:00:00", "gmax@example.com", "emmy.t@example.com", 0, "Gerichtsakten Maxwell 2021"},
                {29, 1, "Inventar Palm Beach", "Alle Kameras funktionieren. Neue im Gaestezimmer installiert.", "2004-12-01 11:00:00", "rodriguez@example.com", "jeff@example.com", 0, "Palm Beach PD"},
                {1, 25, "Immobilien Update", "Pruefe bitte den Mietvertrag fuer die 66th Street.", "2003-06-15 09:00:00", "jeff@example.com", "mark.e@example.com", 1, "Gerichtsakten 2019 SDNY"}
        };

        for (Object[] d : daten) {
            ps.setInt(1, (int) d[0]);
            ps.setInt(2, (int) d[1]);
            ps.setString(3, (String) d[2]);
            ps.setString(4, (String) d[3]);
            ps.setString(5, (String) d[4]);
            ps.setString(6, (String) d[5]);
            ps.setString(7, (String) d[6]);
            ps.setInt(8, (int) d[7]);
            ps.setString(9, (String) d[8]);
            ps.addBatch();
        }
        int count = ps.executeBatch().length;
        ps.close();
        System.out.println("  [OK] Lukas_emails:             " + count + " Eintraege");
        return count;
    }
    private static int fuelleDokumente() throws SQLException {
        String sql = "INSERT INTO Lukas_dokumente (titel, dokument_typ, datum, inhalt_auszug, dateiname, quelle, klassifizierung) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        String[][] daten = {
                {"Non-Prosecution Agreement Florida", "Gerichtsakte", "2008-06-30", "Vereinbarung zwischen Staatsanwaltschaft und Epstein.", "NPA_2008.pdf", "US Attorney SD Florida", "Oeffentlich"},
                {"Anklageschrift SDNY 2019", "Gerichtsakte", "2019-07-08", "Anklage wegen Sexhandel mit Minderjaehrigen.", "Indictment_19CR490.pdf", "SDNY", "Oeffentlich"},
                {"Schwarzes Buch (Little Black Book)", "Sonstiges", "2004-01-01", "Adressbuch mit ueber 1500 Namen und Nummern.", "black_book.pdf", "Palm Beach PD", "Entsiegelt"},
                {"Fluglogbuch Boeing 727 (1995-2013)", "Sonstiges", "2013-12-31", "Vollstaendige Fluglogs mit Passagierlisten.", "flight_logs_727.pdf", "FAA", "Entsiegelt"},
                {"Virginia Giuffre vs. Ghislaine Maxwell", "Gerichtsakte", "2015-09-21", "Zivilklage wegen Diffamierung.", "Giuffre_v_Maxwell.pdf", "SDNY", "Teilgeschwaerzt"},
                {"Palm Beach Police Report", "Polizeibericht", "2005-11-01", "Erster Polizeibericht nach Anzeige.", "PB_Police_Report.pdf", "Palm Beach PD", "Oeffentlich"},
                {"FBI Ermittlungsbericht", "Polizeibericht", "2006-08-15", "FBI-Bericht mit Aussagen von ueber 30 Opfern.", "FBI_Investigation.pdf", "FBI Miami", "Teilgeschwaerzt"},
                {"Giuffre Deposition", "Gerichtsakte", "2016-05-03", "Mehrstuendige Aussage unter Eid.", "Giuffre_Deposition.pdf", "SDNY", "Teilgeschwaerzt"},
                {"Maxwell Anklageschrift", "Gerichtsakte", "2020-07-02", "Anklage gegen Maxwell wegen Beihilfe.", "Maxwell_Indictment.pdf", "SDNY", "Oeffentlich"},
                {"MC2 Geschaeftsunterlagen", "Finanzdokument", "2005-06-01", "Zahlungsstroeme von Epstein an Brunel.", "MC2_financials.pdf", "Ermittlung Frankreich", "Vertraulich"},
                {"Epstein Finanzuebersicht", "Finanzdokument", "2019-07-15", "Vermoegen geschaetzt 577 Mio USD.", "Epstein_Finances.pdf", "SDNY", "Teilgeschwaerzt"},
                {"Autopsiebericht Epstein", "Sonstiges", "2019-08-16", "Todesursache: Suizid durch Erhaengen.", "Autopsy_Report.pdf", "NYC Medical Examiner", "Oeffentlich"},
                {"MCC Ueberwachungsvideo-Bericht", "Polizeibericht", "2019-11-19", "Bericht ueber fehlende Ueberwachungsvideos.", "MCC_Video_Report.pdf", "DOJ Inspector General", "Teilgeschwaerzt"},
                {"Victim Impact Statements", "Gerichtsakte", "2022-06-28", "Opferaussagen bei Maxwell-Verurteilung.", "Victim_Statements.pdf", "SDNY", "Oeffentlich"},
                {"FBI Drohnenfotos Little Saint James", "Foto", "2019-08-12", "Drohnenaufnahmen der Insel nach der Razzia.", "LSJ_Photos.zip", "FBI", "Entsiegelt"}
        };

        for (String[] d : daten) {
            for (int i = 0; i < 7; i++) ps.setString(i + 1, d[i]);
            ps.addBatch();
        }
        int count = ps.executeBatch().length;
        ps.close();
        System.out.println("  [OK] Lukas_dokumente:          " + count + " Eintraege");
        return count;
    }
    private static int fuelleDokumentPersonen() throws SQLException {
        String sql = "INSERT INTO Lukas_dokument_personen (dokument_id, person_id, kontext) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        Object[][] daten = {
                {1, 1, "Angeklagter"}, {1, 22, "Staatsanwalt"},
                {2, 1, "Angeklagter"},
                {3, 1, "Eigentuemer"}, {3, 2, "Kontakt"}, {3, 14, "Modelagentur"},
                {3, 26, "Private Nummer"}, {3, 27, "Vermittler"}, {3, 29, "Hat Buch gestohlen"},
                {4, 1, "Flugzeugeigentuemer"}, {4, 11, "Chefpilot"}, {4, 12, "Co-Pilot"},
                {5, 3, "Klaegerin"}, {5, 2, "Beklagte"},
                {6, 1, "Beschuldigter"}, {6, 24, "Ermittler"}, {6, 5, "Erstes Opfer"},
                {7, 1, "Zielperson"}, {7, 3, "Zeugin"}, {7, 5, "Zeugin"},
                {8, 3, "Aussagende"}, {8, 2, "Tatbeteiligte"}, {8, 14, "Rekrutierer"},
                {9, 2, "Angeklagte"},
                {10, 14, "Geschaeftsfuehrer MC2"}, {10, 1, "Finanzier"},
                {11, 1, "Vermoegensinhaber"},
                {12, 1, "Verstorbener"},
                {13, 1, "Inhaftierter"},
                {14, 3, "Aussagende"}, {14, 4, "Aussagende"}, {14, 5, "Aussagende"},
                {14, 6, "Aussagende"}, {14, 2, "Verurteilte"},
                {15, 1, "Inseleigentuemer"}
        };

        for (Object[] d : daten) {
            ps.setInt(1, (int) d[0]);
            ps.setInt(2, (int) d[1]);
            ps.setString(3, (String) d[2]);
            ps.addBatch();
        }
        int count = ps.executeBatch().length;
        ps.close();
        System.out.println("  [OK] Lukas_dokument_personen:  " + count + " Eintraege");
        return count;
    }
    private static int fuelleGerichtsverfahren() throws SQLException {
        String sql = "INSERT INTO Lukas_gerichtsverfahren (aktenzeichen, titel, gericht, verfahrenstyp, status, beginn_datum, ende_datum, urteil, bemerkung) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        String[][] daten = {
                {"08-80736-CR", "USA vs. Epstein (Florida Plea Deal)", "US District Court SD Florida", "Strafverfahren", "Vergleich", "2007-07-01", "2008-06-30", "Non-Prosecution Agreement. 18 Monate County Jail mit Work Release.", "Opfer wurden nicht informiert. Deal spaeter als illegal eingestuft."},
                {"19-CR-490", "USA vs. Epstein (New York)", "US District Court SDNY", "Strafverfahren", "Eingestellt", "2019-07-08", "2019-08-10", "Eingestellt nach Tod am 10.08.2019 im MCC.", "Anklage: Sexhandel und Verschwoerung."},
                {"20-CR-330", "USA vs. Maxwell", "US District Court SDNY", "Strafverfahren", "Abgeschlossen", "2020-07-02", "2021-12-29", "Schuldig in 5 von 6 Punkten. 20 Jahre Haft.", "Verhaftet in Bradford, NH. Prozess: 4 Wochen."},
                {"15-CV-7433", "Giuffre vs. Maxwell (Diffamierung)", "US District Court SDNY", "Zivilklage", "Vergleich", "2015-09-21", "2017-05-25", "Aussergerichtlicher Vergleich. Betrag vertraulich.", "Fuehrte zur Entsiegelung tausender Dokumente."},
                {"08-CV-80380", "Doe vs. USA (CVRA Klage)", "US District Court SD Florida", "Zivilklage", "Abgeschlossen", "2008-07-07", "2019-02-21", "Plea Deal verletzte Crime Victims Rights Act.", "Richter Marra: Opfer haetten informiert werden muessen."},
                {"19-CV-3110", "Epstein Estate Sammelklage", "US Virgin Islands Superior Court", "Sammelklage", "Abgeschlossen", "2019-09-15", "2022-11-30", "Vergleich: 105 Mio USD an Opferfonds.", "Ueber 150 Opfer reichten Ansprueche ein."},
                {"20-CR-381", "USA vs. Brunel (Frankreich)", "Tribunal Judiciaire de Paris", "Strafverfahren", "Eingestellt", "2020-12-16", "2022-02-19", "Eingestellt nach Tod in Haft.", "Brunel in Pariser Gefaengnis tot aufgefunden."},
                {"10-CR-212", "USA vs. Rodriguez (Justizbehinderung)", "US District Court SD Florida", "Strafverfahren", "Abgeschlossen", "2010-01-15", "2011-06-20", "Schuldig. 18 Monate Bundesgefaengnis.", "Butler versuchte schwarzes Buch fuer 50.000 USD zu verkaufen."}
        };

        for (String[] d : daten) {
            for (int i = 0; i < 9; i++) ps.setString(i + 1, d[i]);
            ps.addBatch();
        }
        int count = ps.executeBatch().length;
        ps.close();
        System.out.println("  [OK] Lukas_gerichtsverfahren:  " + count + " Eintraege");
        return count;
    }
    private static int fuelleKontakte() throws SQLException {
        String sql = "INSERT INTO Lukas_kontakte (person_id, telefon, email, adresse, notiz, quelle) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        Object[][] daten = {
                {1, "+1-212-555-0001", "jeff@example.com", "9 East 71st St, NYC", "Hauptwohnsitz", "Schwarzes Buch"},
                {1, "+1-561-555-0002", "jeff.pb@example.com", "358 El Brillo Way, Palm Beach", "Palm Beach Villa", "Schwarzes Buch"},
                {1, "+1-340-555-0003", null, "Little Saint James, USVI", "Insel-Telefon", "Schwarzes Buch"},
                {2, "+1-212-555-0010", "gmax@example.com", "116 East 65th St, NYC", "NYC Wohnung", "Schwarzes Buch"},
                {2, "+44-20-555-0011", "gm.london@example.com", "44 Kinnerton St, London", "London Wohnung", "Schwarzes Buch"},
                {11, "+1-609-555-0020", "larry.v@example.com", null, "Chefpilot", "Schwarzes Buch"},
                {12, "+1-609-555-0021", "david.r@example.com", null, "Co-Pilot", "Schwarzes Buch"},
                {13, "+1-305-555-0030", "nadia.m@example.com", "Miami Beach, FL", "Pilotin", "Schwarzes Buch"},
                {14, "+33-1-555-0040", "jlb@example.com", "22 Avenue Foch, Paris", "MC2 Paris", "Schwarzes Buch"},
                {14, "+1-305-555-0041", "jlb.miami@example.com", "Fisher Island, Miami", "MC2 Miami", "Schwarzes Buch"},
                {15, "+1-212-555-0050", "lesley.g@example.com", null, "Hauptassistentin", "Schwarzes Buch"},
                {16, "+1-212-555-0060", "sarah.k@example.com", null, "Assistentin Rekrutierung", "Schwarzes Buch"},
                {17, "+1-212-555-0070", "adriana.r@example.com", null, "Masseuse", "Schwarzes Buch"},
                {18, "+1-212-555-0080", "miles.a@example.com", "9 East 71st St, NYC", "Hausverwalter", "Schwarzes Buch"},
                {19, "+1-340-555-0090", null, "Little Saint James", "Haushaltshilfe Insel", "Schwarzes Buch"},
                {20, "+1-212-555-0100", null, null, "Chauffeur NYC + PBI", "Schwarzes Buch"},
                {25, "+1-212-555-0110", "mark.e@example.com", "301 East 66th St, NYC", "Bruder Immobilien", "Schwarzes Buch"},
                {26, "+1-212-555-0120", "eva.d@example.com", null, "Ex-Freundin", "Schwarzes Buch"},
                {27, "+7-495-555-0130", "peter.l@example.com", "Moskau / New York", "Vermittler Russland", "Schwarzes Buch"},
                {29, "+1-561-555-0140", null, "Palm Beach, FL", "Butler - UNZUVERLAESSIG", "Schwarzes Buch"},
                {30, "+44-20-555-0150", "emmy.t@example.com", "London", "Assistentin Maxwell London", "Schwarzes Buch"},
                {28, "+1-561-555-0160", null, "Palm Beach, FL", "Kronzeugin", "Polizeiakten"},
                {21, "+1-561-555-0170", "brad.e@example.com", "Fort Lauderdale, FL", "Opfer-Anwalt", "Oeffentlich"},
                {23, "+1-305-555-0180", "julie.b@example.com", "Miami, FL", "Journalistin Herald", "Oeffentlich"},
                {24, "+1-561-555-0190", null, "Palm Beach, FL", "Detective Palm Beach PD", "Polizeiakten"}
        };

        for (Object[] d : daten) {
            ps.setInt(1, (int) d[0]);
            ps.setString(2, (String) d[1]);
            ps.setString(3, (String) d[2]);
            ps.setString(4, (String) d[3]);
            ps.setString(5, (String) d[4]);
            ps.setString(6, (String) d[5]);
            ps.addBatch();
        }
        int count = ps.executeBatch().length;
        ps.close();
        System.out.println("  [OK] Lukas_kontakte:           " + count + " Eintraege");
        return count;
    }
}
