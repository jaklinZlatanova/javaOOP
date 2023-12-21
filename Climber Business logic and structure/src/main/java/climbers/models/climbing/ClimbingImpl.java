package climbers.models.climbing;

import climbers.models.climber.Climber;
import climbers.models.mountain.Mountain;

import java.util.Collection;
import java.util.Iterator;

public class ClimbingImpl  implements  Climbing{


    @Override
    public void conqueringPeaks(Mountain mountain, Collection<Climber> climbers) {

        Collection<String> mountainPeakList = mountain.getPeaksList();
       for (Climber climber : climbers){

           while (climber.canClimb() && mountainPeakList.iterator().hasNext()){
               climber.climb();

               String currentPeaks = mountainPeakList.iterator().next();
               climber.getRoster().getPeaks().add(currentPeaks);
               mountainPeakList.remove(currentPeaks);
           }


//           Iterator<String> iterator = mountain.getPeaksList().iterator();
//
//           while (iterator.hasNext()){
//               if (!climber.canClimb()){
//                   break;
//               }
//               climber.climb();
//
//               String item = iterator.next();
//
//               climber.getRoster().getPeaks().add(item);
//
//               iterator.remove();
//           }
       }
    }
}
