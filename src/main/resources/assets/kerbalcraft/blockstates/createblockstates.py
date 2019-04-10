names = ["rcs", "sas", "brake", "comms", "descending", "electriccharge", "gear", "light", "liquidfuel", "monopropellant", "orbit", "oxidiser", "solidfuel", "xenon"]

for name in names:

    with open(name + "_indicator.json", "w+") as f:
        f.write(
            f"""
{{
"forge_marker": 1,
  "defaults": {{
    "textures": {{
      "all": "kerbalcraft:blocks/{name}_indicator_unpowered"
    }},
    "model": "cube_all",
    "uvlock": true
  }},
  "variants": {{
    "normal": [{{}}],
    "powered": {{
      "true": {{"textures":  {{"all": "kerbalcraft:blocks/{name}_indicator_powered"}}}},
      "false": {{}}
    }},
    "inventory": [{{}}]
  }}
}}
            """)
