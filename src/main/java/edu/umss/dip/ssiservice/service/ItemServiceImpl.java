/**
 * @author: Edson A. Terceros T.
 */

package edu.umss.dip.ssiservice.service;

import edu.umss.dip.ssiservice.model.Item;
import edu.umss.dip.ssiservice.repositories.GenericRepository;
import edu.umss.dip.ssiservice.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ItemServiceImpl extends GenericServiceImpl<Item> implements ItemService {
    private final ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Item> getRepository() {
        return repository;
    }

    @Override
    public void saveImage(Long id, InputStream file) {
        Item itemPersisted = findById(id);
        try {
            Byte[] bytes = ImageUtils.inputStreamToByteArray(file);
            itemPersisted.setImage(bytes);
            getRepository().save(itemPersisted);
        } catch (IOException e) {
            logger.error("Error reading file", e);
            e.printStackTrace();
        }
    }
}
