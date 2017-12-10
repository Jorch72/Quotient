# Quotient

**Note**: Quotient is still a pretty heavy WIP, most of this README does
not yet apply.

Quotient is a simple tool and associated schematic file format that is
resilient to ID map mismatches and the 1.13 flattening, and takes up a
small amount of space based on an internal dictionary. It supports blocks,
tile entities, entities, and arbitrary "metaproperties". It's designed with
Forge ID map mismatches in mind, and was written to allow copying pieces of
a world with a broken ID map to a new world with a fresh ID map.


## Why?

Right now, there are two popular schematic formats. MCEdit-format schematic
and vanilla's NBT structure format. The MCEdit schematic format is extremely
old and does not handle ID map mismatches, and is likely to break once the
1.13 flattening occurs.

The vanilla structure format doesn't have these issues, but it's an incredibly
inefficient format, and tool support for it is extremely bad. Not to mention
structure blocks have a fairly small size limit.


## How?

Quotient is available as a few things; a Java library, an adapter to use it
as an in-game tool for Forge (1.12, 1.10, and 1.7), and an adapter to add it
as a schematic format to WorldEdit. Ports of the Forge frontend to Bukkit
and Sponge are planned but not very high priority, since WorldEdit is a
common and well-tested tool on those platforms.


## Where?

You can download Quotient from [GitHub Releases]() or the [Elytra CI](). The
former is stable builds, the latter is experimental builds.
