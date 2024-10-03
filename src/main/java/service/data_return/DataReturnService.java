package service.data_return;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class DataReturnService {


    public DataReturn success(Object object){
        return DataReturn.builder().code("00").errorMessage("").status(true).data(object).timestamp(LocalDateTime.now()).build();
    }

    public DataReturn failure(String message){
        return DataReturn.builder().code("03").errorMessage(message).status(false).data(null).timestamp(LocalDateTime.now()).build();
    }

    public DataReturn notFoundObject(String message){
        return DataReturn.builder().code("04").errorMessage(message).status(false).data(null).timestamp(LocalDateTime.now()).build();
    }

    public DataReturn tokenExpired(String message){
        return DataReturn.builder().code("05").errorMessage(message).status(false).data(null).timestamp(LocalDateTime.now()).build();
    }

    public DataReturn dataNotFound(String message){
        return DataReturn.builder().code("06").errorMessage(message).status(false).data(null).timestamp(LocalDateTime.now()).build();
    }


    public DataReturn authorization(){
        return DataReturn.builder().code("01").errorMessage("Bạn không có quyền truy cập vào endpoint này!").status(false).data(null).timestamp(LocalDateTime.now()).build();
    }

    public DataReturn endpointNotFound(){
        return DataReturn.builder().code("02").errorMessage("Endpoint không tồn tại!").status(false).data(null).timestamp(LocalDateTime.now()).build();
    }
}
