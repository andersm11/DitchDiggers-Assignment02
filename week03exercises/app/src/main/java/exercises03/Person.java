package exercises03;
public class Person {
    private static long nextId = 0;
    private final long id;
    private String name;
    private int zip;
    private String address;

    public Person() {
        synchronized (Person.class) {
            this.id = nextId++;
        }
    }

    public Person(long initialId) {
        synchronized (Person.class) {
            if (nextId == 0) {
                nextId = initialId;
            }
            this.id = nextId++;
        }
    }

    public synchronized void setZipAndAddress(int n_zip, String n_address) {
        this.zip = n_zip;
        this.address = n_address;
    }
    public synchronized void setName(String n_name) {
        this.name = n_name;
    }
    public long getId() {
        long id_cp = id;
        return id_cp;
    }
    public String getName() {
        String name_cp = name;
        return name_cp;
    }
    public synchronized int getZip() {
        int zip_cp = zip;
        return zip_cp;
    }
    public synchronized String getAddress() {
        String address_cp = address;
        return address_cp;
    }
    public static void main(String[] args) {
        final int numThreads = 10;

        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(() -> {
                Person person = new Person(1);

                long id = person.getId();
                String name = "Person" + id;
                int zip = 2300;
                String address = "Rued Langgaards Vej 7";

                person.setName(name);
                person.setZipAndAddress(zip, address);

                System.out.println("Person with id: " + id +", Name: " + person.getName() +", Zip: " + person.getZip() +", Address: " + person.getAddress());
            });
            thread.start();
        }
    }
}



