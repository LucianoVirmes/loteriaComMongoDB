package br.edu.unoesc.conexao;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.DocumentCodecProvider;
import org.bson.codecs.IterableCodecProvider;
import org.bson.codecs.MapCodecProvider;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.DBObjectCodecProvider;
import com.mongodb.DBRefCodecProvider;
import com.mongodb.DocumentToDBRefTransformer;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.codecs.GridFSFileCodecProvider;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;

public class ConexaoMongo {
	private static MongoClient mongoClient;
	private static MongoDatabase database;

	static {
		CodecRegistry pojoCodecRegistry = fromRegistries(
				fromProviders(new ValueCodecProvider(), new BsonValueCodecProvider(), new DBRefCodecProvider(),
						new DBObjectCodecProvider(), new DocumentCodecProvider(new DocumentToDBRefTransformer()),
						new IterableCodecProvider(new DocumentToDBRefTransformer()),
						new MapCodecProvider(new DocumentToDBRefTransformer()), new GeoJsonCodecProvider(),
						new GridFSFileCodecProvider(), PojoCodecProvider.builder().automatic(true).build()));

		MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(pojoCodecRegistry).build();
		mongoClient = MongoClients.create(settings);
		database = mongoClient.getDatabase("loteria");
	}

	public static MongoDatabase getDatabase() {
		return database;
	}

}
