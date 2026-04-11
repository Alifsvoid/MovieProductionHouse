package com.spring26.section2.group13.movieproductionhouse.akkhorik.service;


import com.spring26.section2.group13.movieproductionhouse.akkhorik.model.*;
import com.spring26.section2.group13.movieproductionhouse.akkhorik.util.FileUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataService {
    private static final String FILMS_FILE = "films.dat";
    private static final String LICENSES_FILE = "licenses.dat";
    private static final String SCREENINGS_FILE = "screenings.dat";
    private static final String BOXOFFICE_FILE = "boxoffice.dat";
    private static final String PITCHES_FILE = "pitches.dat";
    private static final String BUDGETS_FILE = "budgets.dat";
    private static final String TALENT_DEALS_FILE = "talent_deals.dat";
    private static final String TEST_SCREENINGS_FILE = "test_screenings.dat";

    // ==================== Film Methods ====================
    public List<Film> loadFilms() {
        List<String> lines = FileUtil.readLines(FILMS_FILE);
        List<Film> films = new ArrayList<>();
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            if (parts.length >= 8) {
                films.add(new Film(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]),
                        parts[4], parts[5], Double.parseDouble(parts[6]), parts[7]));
            }
        }
        return films;
    }

    public List<Film> loadAvailableFilms() {
        return loadFilms().stream()
                .filter(f -> f.getStatus().equalsIgnoreCase("Available"))
                .collect(Collectors.toList());
    }

    public Film findFilmById(String id) {
        return loadFilms().stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

    public void saveFilm(Film film) {
        String line = String.join("|", film.getId(), film.getTitle(), film.getGenre(),
                String.valueOf(film.getDuration()), film.getRating(), film.getStatus(),
                String.valueOf(film.getLicensingFee()), film.getStudioSharePercent());
        FileUtil.appendLine(FILMS_FILE, line);
    }

    public void updateFilm(Film film) {
        List<Film> films = loadFilms();
        List<String> lines = new ArrayList<>();
        for (Film f : films) {
            if (f.getId().equals(film.getId())) {
                lines.add(String.join("|", film.getId(), film.getTitle(), film.getGenre(),
                        String.valueOf(film.getDuration()), film.getRating(), film.getStatus(),
                        String.valueOf(film.getLicensingFee()), film.getStudioSharePercent()));
            } else {
                lines.add(String.join("|", f.getId(), f.getTitle(), f.getGenre(),
                        String.valueOf(f.getDuration()), f.getRating(), f.getStatus(),
                        String.valueOf(f.getLicensingFee()), f.getStudioSharePercent()));
            }
        }
        FileUtil.writeLines(FILMS_FILE, lines);
    }

    // ==================== License Methods ====================
    public List<License> loadLicenses() {
        List<String> lines = FileUtil.readLines(LICENSES_FILE);
        List<License> licenses = new ArrayList<>();
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            if (parts.length >= 10) {
                License license = new License(parts[0], parts[1], parts[2],
                        LocalDate.parse(parts[3]), LocalDate.parse(parts[4]), parts[5],
                        Integer.parseInt(parts[6]), Double.parseDouble(parts[7].replace("%", "")));
                license.setStatus(parts[8]);
                if (parts.length > 9 && !parts[9].isEmpty()) {
                    license.setKdmKey(parts[9]);
                }
                licenses.add(license);
            }
        }
        return licenses;
    }

    public List<License> loadAllLicenses() {
        return loadLicenses();
    }

    public License findLicenseById(String id) {
        return loadLicenses().stream().filter(l -> l.getLicenseId().equals(id)).findFirst().orElse(null);
    }

    public void saveLicense(License license) {
        String line = String.join("|", license.getLicenseId(), license.getFilmId(), license.getExhibitorName(),
                license.getStartDate().toString(), license.getEndDate().toString(), license.getRegion(),
                String.valueOf(license.getNumberOfScreens()), license.getRevenueSharePercent() + "%",
                license.getStatus(), license.getKdmKey() != null ? license.getKdmKey() : "");
        FileUtil.appendLine(LICENSES_FILE, line);
    }

    public void updateLicense(License license) {
        List<License> licenses = loadLicenses();
        List<String> lines = new ArrayList<>();
        for (License l : licenses) {
            if (l.getLicenseId().equals(license.getLicenseId())) {
                lines.add(String.join("|", license.getLicenseId(), license.getFilmId(), license.getExhibitorName(),
                        license.getStartDate().toString(), license.getEndDate().toString(), license.getRegion(),
                        String.valueOf(license.getNumberOfScreens()), license.getRevenueSharePercent() + "%",
                        license.getStatus(), license.getKdmKey() != null ? license.getKdmKey() : ""));
            } else {
                lines.add(String.join("|", l.getLicenseId(), l.getFilmId(), l.getExhibitorName(),
                        l.getStartDate().toString(), l.getEndDate().toString(), l.getRegion(),
                        String.valueOf(l.getNumberOfScreens()), l.getRevenueSharePercent() + "%",
                        l.getStatus(), l.getKdmKey() != null ? l.getKdmKey() : ""));
            }
        }
        FileUtil.writeLines(LICENSES_FILE, lines);
    }

    // ==================== Screening Methods ====================
    public List<Screening> loadScreenings() {
        List<String> lines = FileUtil.readLines(SCREENINGS_FILE);
        List<Screening> screenings = new ArrayList<>();
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            if (parts.length >= 7) {
                Screening screening = new Screening(parts[0], parts[1], parts[2],
                        LocalDateTime.parse(parts[3]), Integer.parseInt(parts[4]),
                        Integer.parseInt(parts[5]), Double.parseDouble(parts[6]));
                if (parts.length > 7) {
                    screening.setTicketsSold(Integer.parseInt(parts[7]));
                }
                screenings.add(screening);
            }
        }
        return screenings;
    }

    public List<Screening> loadAllScreenings() {
        return loadScreenings();
    }

    public void saveScreening(Screening screening) {
        String line = String.join("|", screening.getScreeningId(), screening.getLicenseId(), screening.getFilmId(),
                screening.getShowtime().toString(), String.valueOf(screening.getScreenNumber()),
                String.valueOf(screening.getCapacity()), String.valueOf(screening.getTicketPrice()),
                String.valueOf(screening.getTicketsSold()));
        FileUtil.appendLine(SCREENINGS_FILE, line);
    }

    public void updateScreening(Screening screening) {
        List<Screening> screenings = loadScreenings();
        List<String> lines = new ArrayList<>();
        for (Screening s : screenings) {
            if (s.getScreeningId().equals(screening.getScreeningId())) {
                lines.add(String.join("|", screening.getScreeningId(), screening.getLicenseId(), screening.getFilmId(),
                        screening.getShowtime().toString(), String.valueOf(screening.getScreenNumber()),
                        String.valueOf(screening.getCapacity()), String.valueOf(screening.getTicketPrice()),
                        String.valueOf(screening.getTicketsSold())));
            } else {
                lines.add(String.join("|", s.getScreeningId(), s.getLicenseId(), s.getFilmId(),
                        s.getShowtime().toString(), String.valueOf(s.getScreenNumber()),
                        String.valueOf(s.getCapacity()), String.valueOf(s.getTicketPrice()),
                        String.valueOf(s.getTicketsSold())));
            }
        }
        FileUtil.writeLines(SCREENINGS_FILE, lines);
    }

    // ==================== Project Pitch Methods ====================
    public List<ProjectPitch> loadPitches() {
        List<String> lines = FileUtil.readLines(PITCHES_FILE);
        List<ProjectPitch> pitches = new ArrayList<>();
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            if (parts.length >= 6) {
                ProjectPitch pitch = new ProjectPitch(parts[0], parts[1], parts[2], parts[3],
                        Double.parseDouble(parts[4]), parts[5]);
                if (parts.length > 6) pitch.setStatus(parts[6]);
                if (parts.length > 7) pitch.setRating(Integer.parseInt(parts[7]));
                if (parts.length > 8) pitch.setComments(parts[8]);
                if (parts.length > 9) pitch.setApprovedBudget(Double.parseDouble(parts[9]));
                pitches.add(pitch);
            }
        }
        return pitches;
    }

    public List<ProjectPitch> loadAllPitches() {
        return loadPitches();
    }

    public ProjectPitch findPitchById(String id) {
        return loadPitches().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public void savePitch(ProjectPitch pitch) {
        String line = String.join("|", pitch.getId(), pitch.getTitle(), pitch.getProducer(),
                pitch.getSynopsis(), String.valueOf(pitch.getEstimatedBudget()), pitch.getGenre(),
                pitch.getStatus(), String.valueOf(pitch.getRating()), pitch.getComments(),
                String.valueOf(pitch.getApprovedBudget()));
        FileUtil.appendLine(PITCHES_FILE, line);
    }

    public void updatePitch(ProjectPitch pitch) {
        List<ProjectPitch> pitches = loadPitches();
        List<String> lines = new ArrayList<>();
        for (ProjectPitch p : pitches) {
            if (p.getId().equals(pitch.getId())) {
                lines.add(String.join("|", pitch.getId(), pitch.getTitle(), pitch.getProducer(),
                        pitch.getSynopsis(), String.valueOf(pitch.getEstimatedBudget()), pitch.getGenre(),
                        pitch.getStatus(), String.valueOf(pitch.getRating()), pitch.getComments(),
                        String.valueOf(pitch.getApprovedBudget())));
            } else {
                lines.add(String.join("|", p.getId(), p.getTitle(), p.getProducer(),
                        p.getSynopsis(), String.valueOf(p.getEstimatedBudget()), p.getGenre(),
                        p.getStatus(), String.valueOf(p.getRating()), p.getComments(),
                        String.valueOf(p.getApprovedBudget())));
            }
        }
        FileUtil.writeLines(PITCHES_FILE, lines);
    }

    // ==================== Budget Methods ====================
    public List<Budget> loadBudgets() {
        // Simplified - in real implementation would parse line items
        List<String> lines = FileUtil.readLines(BUDGETS_FILE);
        List<Budget> budgets = new ArrayList<>();
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            if (parts.length >= 5) {
                Budget budget = new Budget(parts[0], parts[1]);
                budget.setTotalBudget(Double.parseDouble(parts[2]));
                budget.setSpent(Double.parseDouble(parts[3]));
                budget.setStatus(parts[4]);
                budgets.add(budget);
            }
        }
        return budgets;
    }

    public List<Budget> loadAllBudgets() {
        return loadBudgets();
    }

    public void saveBudget(Budget budget) {
        String line = String.join("|", budget.getBudgetId(), budget.getProjectId(),
                String.valueOf(budget.getTotalBudget()), String.valueOf(budget.getSpent()),
                budget.getStatus());
        FileUtil.appendLine(BUDGETS_FILE, line);
    }

    public void updateBudget(Budget budget) {
        List<Budget> budgets = loadBudgets();
        List<String> lines = new ArrayList<>();
        for (Budget b : budgets) {
            if (b.getBudgetId().equals(budget.getBudgetId())) {
                lines.add(String.join("|", budget.getBudgetId(), budget.getProjectId(),
                        String.valueOf(budget.getTotalBudget()), String.valueOf(budget.getSpent()),
                        budget.getStatus()));
            } else {
                lines.add(String.join("|", b.getBudgetId(), b.getProjectId(),
                        String.valueOf(b.getTotalBudget()), String.valueOf(b.getSpent()),
                        b.getStatus()));
            }
        }
        FileUtil.writeLines(BUDGETS_FILE, lines);
    }

    // ==================== Talent Deal Methods ====================
    public List<TalentDeal> loadTalentDeals() {
        List<String> lines = FileUtil.readLines(TALENT_DEALS_FILE);
        List<TalentDeal> deals = new ArrayList<>();
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            if (parts.length >= 6) {
                TalentDeal deal = new TalentDeal(parts[0], parts[1], parts[2], parts[3],
                        Double.parseDouble(parts[4]), Double.parseDouble(parts[5]));
                if (parts.length > 6) deal.setStatus(parts[6]);
                deals.add(deal);
            }
        }
        return deals;
    }

    public TalentDeal findTalentDealById(String id) {
        return loadTalentDeals().stream().filter(d -> d.getDealId().equals(id)).findFirst().orElse(null);
    }

    public void saveTalentDeal(TalentDeal deal) {
        String line = String.join("|", deal.getDealId(), deal.getProjectId(), deal.getTalentName(),
                deal.getRole(), String.valueOf(deal.getBaseSalary()), String.valueOf(deal.getBackendPoints()),
                deal.getStatus());
        FileUtil.appendLine(TALENT_DEALS_FILE, line);
    }

    public void updateTalentDeal(TalentDeal deal) {
        List<TalentDeal> deals = loadTalentDeals();
        List<String> lines = new ArrayList<>();
        for (TalentDeal d : deals) {
            if (d.getDealId().equals(deal.getDealId())) {
                lines.add(String.join("|", deal.getDealId(), deal.getProjectId(), deal.getTalentName(),
                        deal.getRole(), String.valueOf(deal.getBaseSalary()), String.valueOf(deal.getBackendPoints()),
                        deal.getStatus()));
            } else {
                lines.add(String.join("|", d.getDealId(), d.getProjectId(), d.getTalentName(),
                        d.getRole(), String.valueOf(d.getBaseSalary()), String.valueOf(d.getBackendPoints()),
                        d.getStatus()));
            }
        }
        FileUtil.writeLines(TALENT_DEALS_FILE, lines);
    }

    // ==================== Financial Calculation Methods ====================
    public double getTotalRevenueForFilm(String filmId) {
        List<Screening> screenings = loadScreenings().stream()
                .filter(s -> s.getFilmId().equals(filmId))
                .collect(Collectors.toList());
        return screenings.stream().mapToDouble(Screening::getRevenue).sum();
    }

    public double getTotalBudgetForProject(String projectId) {
        return loadBudgets().stream()
                .filter(b -> b.getProjectId().equals(projectId))
                .mapToDouble(Budget::getTotalBudget)
                .sum();
    }
}
