package database;

import contracts.Modelable;
import contracts.Repository;
import exeptions.NonExistentModelException;
import utility.Constants;
import exeptions.DuplicateModelException;

import java.util.HashMap;
import java.util.Map;

public class RepositoryImpl<T extends Modelable> implements Repository {
    private Map<String, T> itemsByModel;

    public RepositoryImpl() {
        this.itemsByModel = new HashMap<String, T>();
    }

    protected Map<String, T> getItemsByModel() {
        return this.itemsByModel;
    }

//    protected void setItemsByModel(HashMap<String, T> itemsByModel) {
//        this.itemsByModel = itemsByModel;
//    }

    @Override
    public void add(Modelable item) throws DuplicateModelException {
        if (this.itemsByModel.containsKey(item.getModel()))
        {
            throw new DuplicateModelException(Constants.DUPLICATE_MODEL_MESSAGE);
        }

    }

    @Override
    public T getItem(String model) throws NonExistentModelException {
        if (!this.itemsByModel.containsKey(model))
        {
            throw new NonExistentModelException(Constants.NON_EXISTENT_MODEL_MESSAGE);
        }

        return this.itemsByModel.get(model);
    }

}
