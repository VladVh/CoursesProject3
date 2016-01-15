package com.main;

import com.features.Visual;

import java.util.ArrayList;
import java.util.List;

public class Knives {
    protected List<Knives.Knife> knives;

    public List<Knives.Knife> getKnives() {
        if (knives == null) {
            knives = new ArrayList<Knives.Knife>();
        }
        return this.knives;
    }

    public static class Knife {

        protected String type;
        protected int handy;
        protected String origin;
        protected Visual visual;
        protected String id;

        /**
         * Gets the value of the type property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setType(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the handy property.
         */
        public int getHandy() {
            return handy;
        }

        /**
         * Sets the value of the handy property.
         */
        public void setHandy(int value) {
            this.handy = value;
        }

        /**
         * Gets the value of the origin property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getOrigin() {
            return origin;
        }

        /**
         * Sets the value of the origin property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setOrigin(String value) {
            this.origin = value;
        }

        /**
         * Gets the value of the visual property.
         *
         * @return possible object is
         * {@link Visual }
         */
        public Visual getVisual() {
            return visual;
        }

        /**
         * Sets the value of the visual property.
         *
         * @param value allowed object is
         *              {@link Visual }
         */
        public void setVisual(Visual value) {
            this.visual = value;
        }

        /**
         * Gets the value of the id property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setId(String value) {
            this.id = value;
        }

    }

}
