import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import jdk.jfr.FlightRecorder;
import jdk.jfr.Recording;

class Reproducer {

    public static void main(String[] args) throws InterruptedException, IOException {
        if (!FlightRecorder.isAvailable()) {
            throw new IllegalStateException("Flight Recorder is not available");
        }
        System.out.println(String.format("JVM version %s", Runtime.version().toString()));
        System.out.println("Checking recording states:\n");
        for (int i = 0; i < 3 ; i++) {
            List<Recording> recordings = FlightRecorder.getFlightRecorder().getRecordings();
            System.out.print("\t");
            if (recordings.isEmpty()) {
                System.out.println("NONE");
            }
            for (Recording rec : recordings) {
                System.out.println(
                    """
                    %s (%d): %s
                    """.formatted(rec.getName(), rec.getId(), rec.getState().name())
                        );
            }
            System.out.println(".jfr files within /tmp:");
            Files.walk(Path.of("/tmp/")).filter(Files::isRegularFile).map(Path::getFileName).filter(f -> f.toString().endsWith(".jfr")).map(f -> "\t" + f).forEach(System.out::println);
            System.out.println("========================\n");
            Thread.sleep(3_000);
        }
    }
}
