package net.jesusramirez.cargarimagenes;

//CLASE OBJETO QUE REPRESENTA UN HEROE Y LA INFORMACION QUE GUARDA
public class Heroe {
        String name, imageUrl;
        public Heroe(String name, String imageUrl) {
            this.name = name;
            this.imageUrl = imageUrl;
        }
        public String getName() {
            return name;
        }
        public String getImageUrl() {
            return imageUrl;
        }
}
