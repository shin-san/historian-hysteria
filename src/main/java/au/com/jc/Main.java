package au.com.jc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Integer> location1 = new ArrayList<>();
        List<Integer> location2 = new ArrayList<>();

        final Path filePath = new File("src/main/resources/locations.txt").toPath();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;

            // read it from a .txt file
            while((line = br.readLine()) != null) {
                var locations = line.split("   ");
                location1.add(Integer.valueOf(locations[0]));
                location2.add(Integer.valueOf(locations[1]));
            }

            // sort both arrays
            List<Integer> sortedLocation1 = location1.stream().sorted().toList();
            List<Integer> sortedLocation2 = location2.stream().sorted().toList();

            var totalDistance = 0L;

            for (int i = 0; i < sortedLocation1.size(); i++) {
                var distance = 0L;
                if (sortedLocation1.get(i) > sortedLocation2.get(i)) {
                    distance =  sortedLocation1.get(i) - sortedLocation2.get(i);
                } else {
                    distance = sortedLocation2.get(i) - sortedLocation1.get(i);
                }
                totalDistance += distance;
            }

            System.out.println(totalDistance);

            // part two

            System.out.println(partTwo(location1, location2));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long partTwo(List<Integer> location1, List<Integer> location2) {

        List<Long> similarityScores = new ArrayList<>();

        var totalScore = 0L;

        // part two
        location1.forEach(location1Distance -> {
            var location1Similarity = location2.stream()
                    .filter(location1Distance::equals)
                    .count();

            similarityScores.add(location1Distance * location1Similarity);
        });

        for (Long similarityScore: similarityScores) {
            totalScore += similarityScore;
        }

        return totalScore;
    }
}