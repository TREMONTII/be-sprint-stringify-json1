import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class stringifyJSON {

  public String ObjectMapper(Object data) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(data);
  }

  public String stringify(Object data) {

    //입력된 값이 문자열일 경우
    if (data instanceof String)
    {
      String result = "\""+ data + "\"";
      return result;
    }

    //입력된 값이 Integer일 경우
     if (data instanceof Integer)
    {
      return String.valueOf((int)data);
    }

    //입력된 값이 Boolean일 경우
    if(data instanceof  Boolean)
    {
      if(data.equals(true))
        return "true";
      else if(data.equals(false))
        return "false";
    }

    //입력된 값이 Object[]일 경우

    if(data instanceof  Object[]) {
      if (data instanceof Object[]) {

        Object[] arr = (Object[]) data;
        String[] str = new String[arr.length];
        for (int i = 0; i < str.length; i++)
          str[i] = stringify(arr[i]);

        return Arrays.toString(str).replace(" ", "");

      }
    }

    //입력된 값이 HashMap일 경우
    //해시맵 저장
    //이터레이터 생성
    //여태 작성한 코드를 이용해 key와 value를 stringify 메서드를 거쳐서 string형태로 변환
    if (data instanceof HashMap)
    {
      HashMap hashMap = (HashMap) data;
      Set<String> keySet = hashMap.keySet();

      String result = new String();
      Iterator<String> keyIterator = keySet.iterator();
      if(keySet.size() == 0) return "{}";

      while(keyIterator.hasNext())
      {
        String key = keyIterator.next();
        Object value = hashMap.get(key);
        result = result + stringify(key) + ":" + stringify(value) + ",";
      }

      result =  "{"+ result;
      String result2 = result.substring(0, result.length()-1);

      return result2+"}";
    }

    //지정되지 않은 타입의 경우에는 "null"을 리턴합니다.
    return "null";
  }
}
