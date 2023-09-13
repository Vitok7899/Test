 

import java.math.BigDecimal;

    public class EngineModel {
        public int idEngine;
        public String engineType;
        public double engineCapacity;
        public String cylinderArrange;
        public int numCylinder;

        public int getIdEngine() {
            return idEngine;
        }

        public void setIdEngine(int idEngine) {
            this.idEngine = idEngine;
        }

        public String getEngineType() {
            return engineType;
        }

        public void setEngineType(String engineType) {
            this.engineType = engineType;
        }

        public double getEngineCapacity() {
            return engineCapacity;
        }

        public void setEngineCapacity(double engineCapacity) {
            this.engineCapacity = engineCapacity;
        }

        public String getCylinderArrange() {
            return cylinderArrange;
        }

        public void setCylinderArrange(String cylinderArrange) {
            this.cylinderArrange = cylinderArrange;
        }

        public int getNumCylinder() {
            return numCylinder;
        }

        public void setNumCylinder(int numCylinder) {
            this.numCylinder = numCylinder;
        }
    }
