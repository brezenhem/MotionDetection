package lindenvalley.de.motiondetection.detection;

import android.location.Location;
import java.util.ArrayList;
import lindenvalley.de.motiondetection.constants.Constants;

public class DetectStop {
    private ArrayList<Location> locations = new ArrayList<>();

    private int maxSize = Constants.DEFAULT_MAX_SIZE;
    private int maxDistance =  Constants.DEFAULT_MAX_DISTANCE;;

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public boolean isStop(Location location) {
        if (locations.size() >= maxSize) {
            locations.remove(locations.size() - 1);
        }

        locations.add(0, location);

        double distance = 0;
        for (Location locationFrom : locations) {
            for (Location locationTo : locations) {
                distance = Math.max(distance, locationFrom.distanceTo(locationTo));
            }
        }
        return distance < maxDistance && locations.size() == maxSize;
    }
}
