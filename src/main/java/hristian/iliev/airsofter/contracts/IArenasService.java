package hristian.iliev.airsofter.contracts;

import hristian.iliev.airsofter.models.Arena;
import hristian.iliev.airsofter.models.ArenaCategory;
import hristian.iliev.airsofter.models.Timetable;
import hristian.iliev.airsofter.models.User;
import hristian.iliev.airsofter.models.request.ArenaMainSettings;
import hristian.iliev.airsofter.models.request.LatLng;
import hristian.iliev.airsofter.models.response.ChartData;

import java.text.ParseException;
import java.util.List;

public interface IArenasService {
  Arena getArena(int id);

  Arena createArena(Arena arena, User user);

  ArenaCategory getArenaCategoryByName(String name);

  List<Arena> getAll();

  Arena changeArenaMainSettings(User owner, ArenaMainSettings arenaMainSettings);

  Arena changeLatLng(User owner, LatLng latLng);

  Arena changeTimetable(User owner, Timetable timetable);

  ChartData getChartData(User owner) throws ParseException;

  List<Arena> list();
}
