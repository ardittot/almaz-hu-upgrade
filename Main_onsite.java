import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
 
public class Main_onsite {
 
   public static int executeAdbCommands(List<String> var0) {
      try {
         Iterator<String> var1 = var0.iterator();
         while(var1.hasNext()) {
            // String var2 = var1.next();
            // Runtime var3 = Runtime.getRuntime();
            // Process var4 = var3.exec(var2);

            ProcessBuilder var2 = new ProcessBuilder(new String[] { "bash", "-c", var1.next() });
            var2.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            var2.redirectError();
            Process var4 = var2.start();

            var4.waitFor(240L, TimeUnit.SECONDS);
            new BufferedReader(new InputStreamReader(var4.getInputStream()));
            TimeUnit.SECONDS.sleep(2);
         }
 
         return 1;
      } catch (InterruptedException var6) {
         var6.printStackTrace();
      } catch (IOException var7) {
         var7.printStackTrace();
      }
 
      return 0;
   }
 
   public static int addMenu(int var0, String var1) {
      ++var0;
      System.out.println(var0 + ". " + var1);
      return var0;
   }
 
   public static boolean checkValidIpAddress(String var0) {
      boolean var1 = true;
      boolean var2 = false;
      boolean var3 = false;
      boolean var4 = false;
 
      try {
         int var7 = var0.indexOf(".");
         int var9 = Integer.parseInt(var0.substring(0, var7));
         if (var9 > 0 && var9 < 255) {
            int var8 = var0.indexOf(".", var7 + 1);
            var9 = Integer.parseInt(var0.substring(var7 + 1, var8));
            if (var9 >= 0 && var9 < 255) {
               var7 = var8;
               var8 = var0.indexOf(".", var8 + 1);
               var9 = Integer.parseInt(var0.substring(var7 + 1, var8));
               if (var9 >= 0 && var9 < 255) {
                  if (var0.indexOf(".", var8 + 1) >= 0) {
                     return false;
                  } else {
                     var9 = Integer.parseInt(var0.substring(var8 + 1));
                     return var9 > 0 && var9 < 255;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } catch (Exception var6) {
         return false;
      }
   }

   public static int executeAdbInstall(String var0) {
      ArrayList var1 = new ArrayList();
      //var0 = "./AndroidPackage/tools/totalcommander-filemanager.apk";
      var1.add("./platform-tools/adb install " + var0);
      return executeAdbCommands(var1);
   }
 
   public static void main(String[] var0) {
      new Main_onsite();

      System.out.println("");
      System.out.print("IP address : ");
      Scanner var4 = new Scanner(System.in);
      String var5 = var4.next(); //String var5 = "192.168.1.1";
      boolean var6 = checkValidIpAddress(var5);
      System.out.println("IP address is valid : " + var6);
      System.out.println("");

      ArrayList var10;

      System.out.println("00/15: Connect with Device");
      var10 = new ArrayList();
      var10.add("./platform-tools/adb tcpip 5555");
      var10.add("./platform-tools/adb connect " + var5 + ":5555");
      var10.add("./platform-tools/adb devices");
      executeAdbCommands(var10);

      System.out.println("01/15: Installing File Manager");
      executeAdbInstall("./APK/totalcommander-filemanager.apk");

      System.out.println("02/15: Installing Assistive Touch");
      executeAdbInstall("./APK/touch.apk");

      System.out.println("03/15: Installing Rotation");
      executeAdbInstall("./APK/rotation.apk");

      System.out.println("04/15: Installing GPS Driver");
      executeAdbInstall("./APK/UsbGps4Droid-v2.2.1.apk");

      System.out.println("05/15: Set your GPS connection to Maps app");
      var10 = new ArrayList();
      var10.add("./platform-tools/adb    shell settings put secure location_providers_allowed +gps");
      var10.add("./platform-tools/adb    shell settings put secure location_providers_allowed +network");
      var10.add("./platform-tools/adb    shell settings put secure location_providers_allowed +wifi");
      var10.add("./platform-tools/adb    shell settings put secure location_mode_high_accuracy +wifi");
      var10.add("./platform-tools/adb    shell appops set org.broeuschmeul.android.gps.usb.provider android:mock_location allow");
      var10.add("./platform-tools/adb    shell cmd appops set org.broeuschmeul.android.gps.usb.provider RUN_IN_BACKGROUND allow");
      var10.add("./platform-tools/adb -d shell pm grant org.broeuschmeul.android.gps.usb.provider android.permission.ACCESS_FINE_LOCATION");
      //var10.add("./platform-tools/adb -d shell pm grant org.broeuschmeul.android.gps.usb.provider android.permission.ACCESS_COARSE_LOCATION");
      //var10.add("./platform-tools/adb -d shell pm grant org.broeuschmeul.android.gps.usb.provider android.permission.ACCESS_MOCK_LOCATION");
      var10.add("./platform-tools/adb    shell appops set org.broeuschmeul.android.gps.usb.provider 58 allow");
      //var10.add("./platform-tools/adb    shell setprop ro.allow.mock.location 1");
      executeAdbCommands(var10);

      System.out.println("06/15: Installing Youtube");
      executeAdbInstall("./APK/Vanced.apk");

      System.out.println("07/15: Installing Darker Pro");
      executeAdbInstall("./APK/darkerpro.apk");

      System.out.println("08/15: Installing Autokit");
      executeAdbInstall("./APK/autokit_2022.apk");

      System.out.println("09/15: Installing Vidio");
      executeAdbInstall("./APK/vidiomod.apk");

      System.out.println("10/15: Installing Maps - Waze");
      executeAdbInstall("./APK/Wazev4.78.0.2.apk");

      System.out.println("11/15: Installing Vanced GMS");
      executeAdbInstall("./APK/Vancedmicrogcom.apk");

      System.out.println("12/15: Installing Spotify");
      executeAdbInstall("./APK/Spotify.apk");

      System.out.println("13/15: Installing Web Browser - Opera");
      executeAdbInstall("./APK/OperaMini_v59.0.2254.59208.apk");

      System.out.println("14/15: Installing HeadUnit Reloaded");
      executeAdbInstall("./APK/HUReloaded.apk");

      System.out.println("15/15: Installing Sound EQ");
      executeAdbInstall("./APK/audio.sound.effect.bass.virtrualizer.equalizer_2022-10-31.apk");

      System.out.println("Finished: Exiting...");
      var10 = new ArrayList();
      var10.add("./platform-tools/adb kill-server");
      executeAdbCommands(var10);

      return;

   }
}
 