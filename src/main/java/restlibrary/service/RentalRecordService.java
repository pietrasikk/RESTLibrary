package restlibrary.service;

import java.util.List;

public interface RentalRecordService {

    String rentBooks(Long userId, List<Long> booksIds);
}
