package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kaishu on 13/3/19.
 */

public class NewSchedule {

    @Expose
    private String message;

    @Expose
    @SerializedName("events")
    private List<OuterEvents> outerEvents;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OuterEvents> getEvents() {
        return outerEvents;
    }

    public void setEvents(List<OuterEvents> events) {
        this.outerEvents = events;
    }

    public class OuterEvents {
        @Expose
        public int day;

        @Expose
        public List<Events> events;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public List<Events> getEvents() {
            return events;
        }

        public void setEvents(List<Events> events) {
            this.events = events;
        }

        public class Events {
            @Expose
            private List<Schedule> schedule;

            @Expose
            private String venue;

            @Expose
            private String thumbnail;

            @Expose
            private String name;

            @Expose
            private String tagline;

            @Expose
            private int id;

            public List<Schedule> getSchedule() {
                return schedule;
            }

            public void setSchedule(List<Schedule> schedule) {
                this.schedule = schedule;
            }

            public String getVenue() {
                return venue;
            }

            public void setVenue(String venue) {
                this.venue = venue;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTagline() {
                return tagline;
            }

            public void setTagline(String tagline) {
                this.tagline = tagline;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }


            public class Schedule {
                @Expose
                private String time;

                @Expose
                private int day;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

            }


        }
    }

}
