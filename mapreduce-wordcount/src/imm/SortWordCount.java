package imm;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class SortWordCount {

  public static class Map extends Mapper<LongWritable, Text, IntWritable, Text> {

    private IntWritable count = new IntWritable(0);
    private Text word = new Text();

    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
      String line = value.toString();
      String[] splitted = line.split("\t");
      String wordStr = splitted[0];
      int countInt = Integer.parseInt(splitted[1]);

      count.set(countInt);
      word.set(wordStr);
      context.write(count, word);
    }
  }

  public static class Reduce extends Reducer<IntWritable, Text, IntWritable, Text> {

    public void reduce(IntWritable key, Iterable<Text> values, Context context)
        throws IOException, InterruptedException {
      StringBuilder wordsSb = new StringBuilder();

      for (Text val : values) {
        wordsSb.append(val.toString() + " ");
      }

      context.write(key, new Text(wordsSb.toString()));
    }
  }

  public static void main(String[] args)
      throws Exception {
    Configuration conf = new Configuration();

    Job job = new Job(conf, "SortWordCount");

    job.setJarByClass(SortWordCount.class);

    job.setOutputKeyClass(IntWritable.class);
    job.setOutputValueClass(Text.class);

    job.setMapperClass(Map.class);
    job.setReducerClass(Reduce.class);

    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.waitForCompletion(true);
  }

}
