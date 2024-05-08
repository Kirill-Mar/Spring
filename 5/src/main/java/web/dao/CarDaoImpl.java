package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarDaoImpl implements CarDao {
    private static final List<Car> carList = new ArrayList<>();

    static {
        carList.add(new Car("Vaz 2107", "black", 2001));
        carList.add(new Car("Zaporozhets", "grey", 1998));
        carList.add(new Car("Honda Civic", "red", 2005));
        carList.add(new Car("Ford Focus", "white", 2008));
        carList.add(new Car("Toyota Corolla", "blue", 2009));
    }

    @Override
    public List<Car> getCars(int count) {
        if (count == 0) {
            return carList;
        }

        return carList.stream().limit(count).collect(Collectors.toList());
    }
}
