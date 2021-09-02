package entity;

import jakarta.persistence.*;





@Entity
@NamedQuery(name = "Pizza.findAll", query = "Select p from Pizza p")
    public class Pizza {




        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String name;
        private String ingridients;
        private int price;


        public Pizza() {

        }

    public Pizza(int id, String name, String ingridients, int price) {
        this.id = id;
        this.name = name;
        this.ingridients = ingridients;
        this.price = price;
    }

    public Pizza(String name, String ingridients, int price) {
            this.name = name;
            this.ingridients = ingridients;
            this.price = price;
        }



    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIngridients() {
            return ingridients;
        }

        public void setIngridients(String ingridients) {
            this.ingridients = ingridients;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }


        @Override
        public String toString() {
            return "entity.Pizza id:" + id +
                    ", Name: " + name  +
                    ", Ingridients: " + ingridients +
                    ", Price: " + price;

        }
    }


