package lindenvalley.de.motiondetection;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import lindenvalley.de.motiondetection.callback.DetectStatus;
import lindenvalley.de.motiondetection.detection.DetectStop;

public class DetectionManager implements LocationListener {
    private DetectStop mDetectStop;
    private DetectStatus mCallback;
    private LocationManager mService;
    private Context mContext;

    public DetectionManager(DetectStatus callback, Context context) {
        mContext = context;
        mDetectStop = new DetectStop();
        mCallback = callback;
    }

    public void setMaxSize(int maxSize) {
        mDetectStop.setMaxSize(maxSize);;
    }

    public void setMaxDistance(int maxDistance) {
        mDetectStop.setMaxDistance(maxDistance);
    }

    public void detect() {
        requestLocationUpdates();
    }

    public void removeUpdates() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mService.removeUpdates(this);
    }

    private void requestLocationUpdates() {
        mService = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mService.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null && mCallback != null) {
            if (mDetectStop.isStop(location)) {
                mCallback.onStopMotion();
            } else {
                mCallback.onMotion();
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
