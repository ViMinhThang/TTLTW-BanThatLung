-- On belts.name for fast lookup by name
CREATE INDEX idx_belts_name ON belts (name);

-- On beltVariants to speed up join + where filters
CREATE INDEX idx_beltVariants_beltId_sizeId_colorId
    ON beltVariants (beltId, sizeId, colorId);

-- Optional if querying by size or color
CREATE INDEX idx_sizes_name ON sizes (name);
CREATE INDEX idx_colors_name ON colors (name);