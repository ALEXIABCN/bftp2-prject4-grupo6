package repositories;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
    @Table(name = "games")
    public class Game implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String price;
        private String category;

        public Game() {

        }

        public Game(String title, String price, String category) {
            this.title = title;
            this.price = price;
            this.category = category;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Game{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", price='" + price + '\'' +
                    ", category='" + category + '\'' +
                    '}';
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Game game = (Game) o;
            return Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(price, game.price) && Objects.equals(category, game.category);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title, price, category);
        }
    }


