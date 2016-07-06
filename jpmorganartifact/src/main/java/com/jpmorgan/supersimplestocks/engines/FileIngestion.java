package com.jpmorgan.supersimplestocks.engines;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jpmorgan.supersimplestocks.vos.TradeVO;

public class FileIngestion {

	public static Stream<String> getStream(String filename) {

		Stream<String> stream = null;
		try {
			stream = Files.lines(Paths.get(filename));
			// stream.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream;
	}

	public static Map<String, String> getMapFromCSV(final String fileName) throws IOException {
		return Files.lines(Paths.get(fileName)).map(line -> line.split(","))
				.collect(Collectors.toMap(line -> line[0], line -> line[1]));
	}
	
	public static List<TradeVO> getListFromCSV(final String filename, int limit) throws IOException {
		
		Stream<String> stream = getStream(filename);
		List<TradeVO> collect = (List<TradeVO>)stream.map(mapToTradeVO).limit(limit).collect(Collectors.toList());
		List<TradeVO> list = collect;
		return list;
	}
	
	public static Function<String, TradeVO> mapToTradeVO = (line) -> {
		  String[] s = line.split(",");
		  
		  return new TradeVO(s[0], LocalDateTime.now(), LocalDateTime.now(), Integer.parseInt(s[1]), Double.parseDouble(s[2]), String.valueOf(s[3]));
		};
		
	

}
