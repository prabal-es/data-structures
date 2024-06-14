package ds.stacks_queues;

import java.util.LinkedList;

/**
 * 3.6 Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly"first in,
 * first out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
 * or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
 * that type). They cannot select which specific animal they would like. Create the data structures to
 * maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
 * and dequeueCat. You may use the built-in Linked List data structure.
 * Hints: #22, #56, #63
 *
 * #22 3.6 We could consider keeping a single linked list for dogs and cats, and then iterating
 *         through it to find the first dog (or cat). What is the impact of doing this?
 * #56 3.6 Let's suppose we kept separate lists for dogs and cats. How would we find the oldest
 *         animal of any type? Be creative!
 * #63 3.6 Think about how you'd do it in real life. You have a list of dogs in chronological order and
 *         a list of cats in chronological order. What data would you need to find the oldest animal?
 *         How would you maintain this data?
 * */
public class AnimalShelter {
    private final LinkedList<Dog> dogShelter;
    private final LinkedList<Cat> catShelter;
    private int insertOrder = 0;

    public AnimalShelter() {
        dogShelter = new LinkedList<>();
        catShelter = new LinkedList<>();
    }

    public void enQueue(Animal animal){
        insertOrder++;
        animal.order = insertOrder;
        if(animal instanceof Dog){
            dogShelter.add((Dog) animal);
        }else {
            catShelter.add((Cat) animal);
        }
    }

    public Animal deQueueAny(){
        if(dogShelter.isEmpty()){
            return deQueueCat();
        } else if (catShelter.isEmpty()) {
            return deQueueDog();
        }
        return dogShelter.peek().getOrder() > catShelter.peek().getOrder()? deQueueDog() : deQueueCat();
    }

    public Dog deQueueDog(){
        return dogShelter.poll();
    }

    public Cat deQueueCat(){
        return catShelter.poll();
    }

    public static class Animal{
        private String name;
        private int order;
        public Animal(String name){
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }
    public static class Dog extends Animal{
        public Dog(String name){
            super(name);
        }
    }
    public static class Cat extends Animal{
        public Cat(String name){
            super(name);
        }
    }
}
